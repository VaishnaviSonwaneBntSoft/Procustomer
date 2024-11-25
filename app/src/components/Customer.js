import { SEARCH_CUSTOMER_URL } from "../utils/constants";
import { useState } from "react";
import Search from "./Search";
import CustomerDetail from "./CustomerDetail";

const Customer = () => {
  const [customerList, setCustomerList] = useState([]);
  const [isValidSearchData, setIsValidSearchData] = useState(false);
  const [isCustomerSelected, setIsCustomerSelected] = useState(false);
  const [selectedCustomerData, setSelectedCustomerData] = useState([]);

  const fetchCustomerDataBySearchCriteria = async (customerNumber, firstName, familySurname, 
    emailAddress, phoneNumber, passportNumber, drivingLicenseNumber) => {
      console.log("fetchCustomerDataBySearchCriteria");
      console.log(SEARCH_CUSTOMER_URL);
    const data = await fetch(SEARCH_CUSTOMER_URL,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({ 
          customerNumber: customerNumber,
          firstName: firstName,
          familySurname: familySurname,
          emailAddress: emailAddress,
          phoneNumber: phoneNumber,
          passportNumber: passportNumber,
          drivingLicenseNumber: drivingLicenseNumber,
        })
      }
    );
    const jsonData = await data.json();
    setCustomerList(jsonData);
  };


  const checkIfValidSearchData = (customerNumber, firstName, familySurname, 
    emailAddress, phoneNumber, passportNumber, drivingLicenseNumber) => {

      setIsValidSearchData(true);

      if (customerNumber.trim() == "" 
          && firstName.trim() == "" 
          && familySurname.trim() == "" 
          && emailAddress.trim() == "" 
          && phoneNumber.trim() == "" 
          && passportNumber.trim() == "" 
          && drivingLicenseNumber.trim() == "") {
            setIsValidSearchData(false);
      }
  };

  const setSearchCriteria = (customerNumber, firstName, familySurname, 
    emailAddress, phoneNumber, passportNumber, drivingLicenseNumber) => {

      checkIfValidSearchData(customerNumber, firstName, familySurname, 
        emailAddress, phoneNumber, passportNumber, drivingLicenseNumber)

        console.log("Customer->setSearchCriteria->isValidSearchData");
        console.log(isValidSearchData);

      isValidSearchData && fetchCustomerDataBySearchCriteria(customerNumber, firstName, familySurname, 
        emailAddress, phoneNumber, passportNumber, drivingLicenseNumber);
  }

  const handleCustomerSelectionClick = (index) => {
    setSelectedCustomerData(customerList[index]);
    setIsCustomerSelected(true);
  }

  const customerData = customerList.map((aCustomer, index) => {
    return (
      <tr key={aCustomer?.coreIdentity?.customerNumber}>
        <td className="cursor-pointer text-blue-800 hover:underline" 
            onClick= {(event) => handleCustomerSelectionClick(index)}>{aCustomer?.coreIdentity?.customerNumber}
        </td>
        <td>{aCustomer?.coreIdentity?.tenantName}</td>
        <td>{aCustomer?.coreIdentity?.firstName}</td>
        <td>{aCustomer?.coreIdentity?.familySurname}</td>
        <td>{aCustomer?.emailAddress?.emailAddressData}</td>
        <td>{aCustomer?.coreIdentity?.dateOfBirth}</td>
        <td>{aCustomer?.coreIdentity?.timestamp}</td>
      </tr>
    );
  });

  return (
    <div>
    <div>
      <Search setSearchCriteria={setSearchCriteria} />
      <div className="mt-10 mx-2">
        <table className="table-auto w-full bg-white border border-gray-300">
          <caption className="caption-top">Matched Customers</caption>
          <thead className="bg-blue-200">
            <tr>
              <th>Customer Number</th>
              <th>Tenant Name</th>
              <th>First Name</th>
              <th>Family Surname</th>
              <th>Email Address</th>
              <th>Date Of Birth</th>
              <th>Added</th>
            </tr>
          </thead>
          <tbody>{customerData}</tbody>
        </table>
      </div>
    </div>
    <div>
      { isCustomerSelected && <CustomerDetail selectedCustomerData={selectedCustomerData}/>}
    </div>
    </div>
  );
};

export default Customer;
