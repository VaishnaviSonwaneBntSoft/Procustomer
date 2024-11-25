import { useState } from "react";

const Search = ({setSearchCriteria}) => {

    const [showAdvanceSearch, setShowAdvanceSearch] = useState(false);

    const [customerNumber, setCustomerNumber] = useState("");
    const [firstName, setFirstName] = useState("");
    const [familySurname, setFamilySurname] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [passportNumber, setPassportNumber] = useState("");
    const [drivingLicenseNumber, setDrivingLicenseNumber] = useState("");

    const handleAdvancedSearchClick = () => {
        if(showAdvanceSearch == true) {
            setCustomerNumber("");
            setFirstName("");
            setFamilySurname("");
            setEmailAddress("");
            setPhoneNumber("");
            setPassportNumber("");
            setDrivingLicenseNumber("");
        }
        setShowAdvanceSearch(!showAdvanceSearch);
    };

    const handleSearchClick = () => {
        console.log("handleSearchClick");
        setSearchCriteria(customerNumber, firstName, familySurname, 
            emailAddress, phoneNumber, passportNumber, drivingLicenseNumber);
    };

  return (
    <div className="mt-10 mx-2">
      <div className=" bg-gray-50 shadow-lg p-4">
        <div className="flex justify-between">
            <div>
                <span>
                    <input 
                        className="p-1 border border-solid border-gray-400" 
                        type="text" 
                        id="customer-number-id"
                        value={customerNumber}
                        onChange={(e) => {
                            setCustomerNumber(e.target.value);
                        }}
                        autoFocus
                        placeholder="Customer Number">
                    </input>
                </span>
                <span>
                    <button 
                        className="mx-2 p-2 hover:bg-blue-300 rounded-xl" 
                        onClick={handleSearchClick}>
                        Search
                    </button>
                </span>
            </div>
            <div className="flex justify-end cursor-pointer" onClick={handleAdvancedSearchClick}>
                <span className="text-sm text-blue-800">Advanced Search</span>
            </div>
        </div>
        {showAdvanceSearch &&
            <div>
            <div className="my-2 grid grid-cols-3 gap-6">
                <input
                    className="p-1 border border-solid border-gray-400"
                    type="text"
                    id="first-name-id"
                    value={firstName}
                    onChange={(e) => {
                        setFirstName(e.target.value);
                    }}
                    placeholder="First Name">
                </input>
                <input
                    className="p-1 border border-solid border-gray-400"
                    type="text"
                    id="family-surname-id"
                    value={familySurname}
                    onChange={(e) => {
                        setFamilySurname(e.target.value);
                    }}
                    placeholder="Family Surname">
                </input>
            </div>
            <div className="my-2 grid grid-cols-3 gap-6">
                <input
                    className="p-1 border border-solid border-gray-400"
                    type="text"
                    id="email-address-id"
                    value={emailAddress}
                    onChange={(e) => {
                        setEmailAddress(e.target.value);
                    }}
                    placeholder="Email Address">
                </input>
                <input
                    className="p-1 border border-solid border-gray-400"
                    type="text"
                    id="phone-number-id"
                    value={phoneNumber}
                    onChange={(e) => {
                        setPhoneNumber(e.target.value);
                    }}
                    placeholder="Phone Number">
                </input>
            </div>
            <div className="my-2 grid grid-cols-3 gap-6">
                <input
                    className="p-1 border border-solid border-gray-400"
                    type="text"
                    id="passport-number-id"
                    value={passportNumber}
                    onChange={(e) => {
                        setPassportNumber(e.target.value);
                    }}
                    placeholder="Passport Number">
                </input>
                <input
                    className="p-1 border border-solid border-gray-400"
                    type="text"
                    id="driving-license-number-id"
                    value={drivingLicenseNumber}
                    onChange={(e) => {
                        setDrivingLicenseNumber(e.target.value);
                    }}
                    placeholder="Driving License Number">
                </input>
            </div>
        </div>
        }
      </div>
    </div>
  );
};

export default Search;
