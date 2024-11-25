import { useState } from "react";
import { UPDATE_NATIONAL_IDENTITY_URL } from "../utils/constants";

const NationalIdentity = (props) => {

  const [customerNumber, setCustomerNumber] = useState(props.selectedCustomerNationalIdentityData.customerNumber);

  const [passportNumber, setPassportNumber] = useState(props.selectedCustomerNationalIdentityData.passportNumber);
  const [drivingLicenseNumber, setDrivingLicenseNumber] = useState(props.selectedCustomerNationalIdentityData.drivingLicenseNumber);
  const [nationalTaxId, setNationalTaxId] = useState(props.selectedCustomerNationalIdentityData.nationalTaxId);

  const updateNationalIdentity = async (customerNumber, passportNumber, drivingLicenseNumber, nationalTaxId) => {

    const response = await fetch(UPDATE_NATIONAL_IDENTITY_URL + customerNumber,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
        body: JSON.stringify({ 
          passportNumber: passportNumber,
          drivingLicenseNumber: drivingLicenseNumber,
          nationalTaxId: nationalTaxId
        })
      }
    );

    const status = response.status;

  };

  const handleUpdateNationalIdentity = () => {
    console.log("updateNationalIdentity");
    updateNationalIdentity(customerNumber, passportNumber, drivingLicenseNumber, nationalTaxId);
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
              value={props.selectedCustomerNationalIdentityData.customerNumber} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-passport-number">
              Passport Number
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-passport-number"
              type="text"
              name="passport-number"
              placeholder="Passport Number"
              value={passportNumber}
              onChange={e => setPassportNumber(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-driving-license-number">
              Driving License Number
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-driving-license-number"
              type="text"
              name="driving-license-number"
              placeholder="Driving License Number"
              value={drivingLicenseNumber}
              onChange={e => setDrivingLicenseNumber(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
          <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-national-tax-id">
              National Tax Id
            </label>
            <input
              className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-national-tax-id"
              type="text"
              name="national-tax-id"
              placeholder="National Tax Id"
              value={nationalTaxId}
              onChange={e => setNationalTaxId(e.target.value)} />
          </div>
        </div>
        <div className="flex flex-wrap -mx-3 mb-2">
            <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded"
                        type="submit"
                        onClick={(event) => handleUpdateNationalIdentity()}>
                    Update
                </button>
            </div>
        </div>
    </div>
  );
};

export default NationalIdentity;