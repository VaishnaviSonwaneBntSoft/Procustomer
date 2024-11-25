import { useState } from "react";
import { UPDATE_CORE_IDENTITY_URL } from "../utils/constants";

const CoreIdentity = (props) => {

  const [customerNumber, setCustomerNumber] = useState(props.selectedCustomerCoreIdentityData.customerNumber);
  const [tenantName, setTenantName] = useState(props.selectedCustomerCoreIdentityData.tenantName);

  const [firstName, setFirstName] = useState(props.selectedCustomerCoreIdentityData.firstName);
  const [familySurname, setFamilySurname] = useState(props.selectedCustomerCoreIdentityData.familySurname);
  const [mothersMaidenName, setMothersMaidenName] = useState(props.selectedCustomerCoreIdentityData.mothersMaidenName);

  const [middleName1, setMiddleName1] = useState(props.selectedCustomerCoreIdentityData.middleName1);
  const [middleName2, setMiddleName2] = useState(props.selectedCustomerCoreIdentityData.middleName2);
  const [middleName3, setMiddleName3] = useState(props.selectedCustomerCoreIdentityData.middleName3);

  const [dateOfBirth, setDateOfBirth] = useState(props.selectedCustomerCoreIdentityData.dateOfBirth);
  const [placeOfBirth, setPlaceOfBirth] = useState(props.selectedCustomerCoreIdentityData.placeOfBirth);

  const updateCoreIdentityData = async (customerNumber, tenantName, firstName, familySurname, 
    mothersMaidenName, middleName1, middleName2, middleName3, dateOfBirth, placeOfBirth) => {

    const response = await fetch(UPDATE_CORE_IDENTITY_URL + customerNumber,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
        body: JSON.stringify({ 
          tenantName: tenantName,
          firstName: firstName,
          familySurname: familySurname,
          mothersMaidenName: mothersMaidenName,
          middleName1: middleName1,
          middleName2: middleName2,
          middleName3: middleName3,
          dateOfBirth: dateOfBirth,
          placeOfBirth: placeOfBirth
        })
      }
    );

    const status = response.status;

  };

  const handleUpdateCoreIdentity = () => {
    updateCoreIdentityData(customerNumber, tenantName, firstName, 
      familySurname, mothersMaidenName, middleName1, middleName2, middleName3, dateOfBirth, placeOfBirth);
  }

  return (
    <div className="w-full max-w-2xl">
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-customer-number">
              Customer Number
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-customer-number"
              type="text"
              readOnly={true}
              name="customer-name"
              placeholder="Customer Number"
              value={customerNumber} />
          </div>
          <div className="w-full md:w-1/2 px-3">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-tenant-name">
              Tenant Name
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-tenant-name"
              type="text"
              readOnly={true}
              name="tenant-name"
              placeholder="Tenant Name" 
              value={tenantName} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-first-name">
              First Name
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-first-name"
              type="text"
              name="first-name"
              placeholder="First Name"
              value={firstName}
              onChange={e => setFirstName(e.target.value)} />
          </div>
          <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-family-surname">
              Family Surname
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-family-surname"
              type="text"
              name="family-surname"
              placeholder="Family Surname"
              value={familySurname}
              onChange={e => setFamilySurname(e.target.value)} />
          </div>
          <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-mothers-maiden-name">
              Mothers Maiden Name
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-mothers-maiden-name"
              type="text"
              name="mothers-maiden-name"
              placeholder="Mothers Maiden Name"
              value={mothersMaidenName}
              onChange={e => setMothersMaidenName(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-middle-name-1">
              Middle Name 1
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-middle-name-1"
              type="text"
              name="middle-name-1"
              placeholder="Middle Name 1"
              value={middleName1}
              onChange={e => setMiddleName1(e.target.value)} />
          </div>
          <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-middle-name-2">
              Middle Name 2
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-middle-name-2"
              type="text"
              name="middle-name-2"
              placeholder="Middle Name 2"
              value={middleName2}
              onChange={e => setMiddleName2(e.target.value)} />
          </div>
          <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-middle-name-3">
              Middle Name 3
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-middle-name-3"
              type="text"
              name="middle-name-3"
              placeholder="Middle Name 3"
              value={middleName3}
              onChange={e => setMiddleName3(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-6">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-date-of-birth">
              Date Of Birth
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-date-of-birth"
              type="text"
              name="date-of-birth"
              placeholder="Date Of Birth"
              value={dateOfBirth}
              onChange={e => setDateOfBirth(e.target.value)} />
          </div>
          <div className="w-full md:w-1/2 px-3">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-place-of-birth">
              Place Of Birth
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-place-of-birth"
              type="text"
              name="place-of-birth"
              placeholder="Place Of Birth"
              value={placeOfBirth}
              onChange={e => setPlaceOfBirth(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
            <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded"
                        type="submit"
                        onClick={(event) => handleUpdateCoreIdentity()}>
                    Update
                </button>
            </div>
        </div>
    </div>
  );
};

export default CoreIdentity;
