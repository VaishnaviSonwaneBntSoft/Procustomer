import { useState } from "react";
import { UPDATE_EMAIL_ADDRESS_URL } from "../utils/constants";

const EmailAddress = (props) => {

  const [customerNumber, setCustomerNumber] = useState(props.selectedCustomerEmailAddressData.customerNumber);

  const [emailAddressData, setEmailAddressData] = useState(props.selectedCustomerEmailAddressData.emailAddressData);
  const [emailStatus, setEmailStatus] = useState(props.selectedCustomerEmailAddressData.emailStatus);

  const updateEmailAddress = async (customerNumber, emailAddressData, emailStatus) => {

    const response = await fetch(UPDATE_EMAIL_ADDRESS_URL + customerNumber,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
        body: JSON.stringify({ 
          emailAddressData: emailAddressData,
          emailStatus: emailStatus
        })
      }
    );

    const status = response.status;

  };

  const handleUpdateEmailAddress = () => {
    console.log("updateEmailAddress");
    updateEmailAddress(customerNumber, emailAddressData, emailStatus);
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
              value={props.selectedCustomerEmailAddressData.customerNumber} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-email-address">
              Email Address
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-email-address"
              type="text"
              name="email-address"
              placeholder="Email Address"
              value={emailAddressData}
              onChange={e => setEmailAddressData(e.target.value)} />
          </div>
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-email-status">
              Email Status
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-email-status"
              type="text"
              name="email-status"
              placeholder="Active"
              value={emailStatus}
              onChange={e => setEmailStatus(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded"
              type="submit"
              onClick={(event) => handleUpdateEmailAddress()}>
              Update
            </button>
          </div>
        </div>
    </div>
  );
};

export default EmailAddress;
