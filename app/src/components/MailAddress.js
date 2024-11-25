import { useState } from "react";
import { UPDATE_MAIL_ADDRESS_URL } from "../utils/constants";

const MailAddress = (props) => {

  const [customerNumber, setCustomerNumber] = useState(props.selectedCustomerMailAddressData.customerNumber);

  const [addressType, setAddressType] = useState(props.selectedCustomerMailAddressData.addressType);
  const [buildingNumber, setBuildingNumber] = useState(props.selectedCustomerMailAddressData.buildingNumber);
  const [placeLocation, setPlaceLocation] = useState(props.selectedCustomerMailAddressData.placeLocation);

  const [addressLine1, setAddressLine1] = useState(props.selectedCustomerMailAddressData.addressLine1);
  const [addressLine2, setAddressLine2] = useState(props.selectedCustomerMailAddressData.addressLine2);
  const [addressLine3, setAddressLine3] = useState(props.selectedCustomerMailAddressData.addressLine3);

  const [stateCounty, setStateCounty] = useState(props.selectedCustomerMailAddressData.stateCounty);
  const [country, setCountry] = useState(props.selectedCustomerMailAddressData.country);

  const updateMailAddress = async (customerNumber, addressType, buildingNumber, placeLocation, 
    addressLine1, addressLine2, addressLine3, stateCounty, country) => {

    const response = await fetch(UPDATE_MAIL_ADDRESS_URL + customerNumber,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
        body: JSON.stringify({ 
          addressType: addressType,
          buildingNumber: buildingNumber,
          placeLocation: placeLocation,
          addressLine1: addressLine1,
          addressLine2: addressLine2,
          addressLine3: addressLine3,
          stateCounty: stateCounty,
          country: country
        })
      }
    );

    const status = response.status;

  };

  const handleUpdateMailAddress = () => {
    console.log("updateMailAddress");
    updateMailAddress(customerNumber, addressType, buildingNumber, placeLocation, 
      addressLine1, addressLine2, addressLine3, stateCounty, country);
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
                  value={props.selectedCustomerMailAddressData.customerNumber} />
              </div>
              <div className="w-full md:w-1/2 px-3">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-address-type">
                  Address Type
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-address-type"
                  type="text"
                  name="address-type"
                  placeholder="Residential"
                  value={addressType}
                  onChange={e => setAddressType(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-building-number">
                  Building Number
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-building-number"
                  type="text"
                  name="building-number"
                  placeholder="Building Number"
                  value={buildingNumber}
                  onChange={e => setBuildingNumber(e.target.value)} />
              </div>
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-place-location">
                  Place Location
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-place-location"
                  type="text"
                  name="place-location"
                  placeholder="Place Location"
                  value={placeLocation}
                  onChange={e => setPlaceLocation(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-address-line-1">
                  Address Line 1
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-address-line-1"
                  type="text"
                  name="address-line-1"
                  placeholder="Address Line 1"
                  value={addressLine1}
                  onChange={e => setAddressLine1(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-address-line-2">
                  Address Line 2
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-address-line-2"
                  type="text"
                  name="address-line-2"
                  placeholder="Address Line 2"
                  value={addressLine2}
                  onChange={e => setAddressLine2(e.target.value)} />
              </div>
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-address-line-3">
                  Address Line 3
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-address-line-3"
                  type="text"
                  name="address-line-3"
                  placeholder="Address Line 3"
                  value={addressLine3}
                  onChange={e => setAddressLine3(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-state-county">
                  State County
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-state-county"
                  type="text"
                  name="state-county"
                  placeholder="State County"
                  value={stateCounty}
                  onChange={e => setStateCounty(e.target.value)} />
              </div>
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-country">
                  Country
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-country"
                  type="text"
                  name="country"
                  placeholder="Country"
                  value={country}
                  onChange={e => setCountry(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <button
                  className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded"
                  type="submit"
                  onClick={(event) => handleUpdateMailAddress()}>
                  Update
                </button>
              </div>
            </div>
        </div>
      );
};

export default MailAddress;