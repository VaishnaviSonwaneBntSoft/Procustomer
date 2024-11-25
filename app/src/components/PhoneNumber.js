import { useState } from "react";
import { UPDATE_PHONE_NUMBER_URL } from "../utils/constants";

const PhoneNumber = (props) => {

  const [customerNumber, setCustomerNumber] = useState(props.selectedCustomerPhoneNumberData.customerNumber);

  const [primaryPhoneNumber, setPrimaryPhoneNumber] = useState(props.selectedCustomerPhoneNumberData.primaryPhoneNumber);
  const [primaryPhoneStatus, setPrimaryPhoneStatus] = useState(props.selectedCustomerPhoneNumberData.primaryPhoneStatus);
  const [secondaryPhoneNumber, setSecondaryPhoneNumber] = useState(props.selectedCustomerPhoneNumberData.secondaryPhoneNumber);

  const updatePhoneNumber = async (customerNumber, primaryPhoneNumber, primaryPhoneStatus, secondaryPhoneNumber) => {

    const response = await fetch(UPDATE_PHONE_NUMBER_URL + customerNumber,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
        body: JSON.stringify({ 
          primaryPhoneNumber: primaryPhoneNumber,
          primaryPhoneStatus: primaryPhoneStatus,
          secondaryPhoneNumber: secondaryPhoneNumber
        })
      }
    );

    const status = response.status;

  };

  const handleUpdatePhoneNumber = () => {
    console.log("updatePhoneNumber");
    updatePhoneNumber(customerNumber, primaryPhoneNumber, primaryPhoneStatus, secondaryPhoneNumber);
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
              value={props.selectedCustomerPhoneNumberData.customerNumber} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-primary-phone-number">
              Primary Phone Number
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-primary-phone-number"
              type="text"
              name="primary-phone-number"
              placeholder="Primary Phone Number"
              value={primaryPhoneNumber}
              onChange={e => setPrimaryPhoneNumber(e.target.value)} />
          </div>
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-primary-phone-status">
              Primary Phone Status
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-primary-phone-status"
              type="text"
              name="primary-phone-status"
              placeholder="Active"
              value={primaryPhoneStatus}
              onChange={e => setPrimaryPhoneStatus(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-secondary-phone-number">
              Secondary Phone Number
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-secondary-phone-number"
              type="text"
              name="secondary-phone-number"
              placeholder="Secondary Phone Number"
              value={secondaryPhoneNumber}
              onChange={e => setSecondaryPhoneNumber(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded"
              type="submit"
              onClick={(event) => handleUpdatePhoneNumber()}>
              Update
            </button>
          </div>
        </div>
    </div>
  );
};

export default PhoneNumber;
