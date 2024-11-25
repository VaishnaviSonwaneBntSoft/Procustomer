import { useState } from "react";
import CoreIdentity from "./CoreIdentity";
import DemographicData from "./DemographicData";
import MailAddress from "./MailAddress";
import PhoneNumber from "./PhoneNumber";
import EmailAddress from "./EmailAddress";
import NationalIdentity from "./NationalIdentity";

const CustomerDetail = (props) => {
  const { selectedCustomerData } = props;
  const [activeTab, setActiveTab] = useState("core-identity-tab");

  return (  
    <div className="w-full mx-2 mt-4">
      <h1 className="flex justify-center">Selected Customer Details</h1>
      <div className="flex border-b border-gray-200">
        <button
          className={`py-2 px-4 font-bold ${activeTab === "core-identity-tab" ? "border-b-2 border-blue-500 bg-blue-200" : ""}`}
          onClick={() => setActiveTab("core-identity-tab")}>
          CoreIdentity
        </button>
        <button
          className={`py-2 px-4 font-bold ${activeTab === "demographic-data-tab" ? "border-b-2 border-blue-500 bg-blue-200" : ""}`}
          onClick={() => setActiveTab("demographic-data-tab")}>
          DemographicData
        </button>
        <button
          className={`py-2 px-4 font-bold ${activeTab === "mail-address-tab" ? "border-b-2 border-blue-500 bg-blue-200" : ""}`}
          onClick={() => setActiveTab("mail-address-tab")}>
          MailAddress
        </button>
        <button
          className={`py-2 px-4 font-bold ${activeTab === "phone-number-tab" ? "border-b-2 border-blue-500 bg-blue-200" : ""}`}
          onClick={() => setActiveTab("phone-number-tab")}>
          PhoneNumber
        </button>
        <button
          className={`py-2 px-4 font-bold ${activeTab === "email-address-tab" ? "border-b-2 border-blue-500 bg-blue-200" : ""}`}
          onClick={() => setActiveTab("email-address-tab")}>
          EmailAddress
        </button>
        <button
          className={`py-2 px-4 font-bold ${activeTab === "national-identity-tab" ? "border-b-2 border-blue-500 bg-blue-200" : ""}`}
          onClick={() => setActiveTab("national-identity-tab")}>
          NationalIdentity
        </button>
      </div>
      <div className="p-4">
        {activeTab === "core-identity-tab" && <CoreIdentity selectedCustomerCoreIdentityData={selectedCustomerData.coreIdentity}/> }
        {activeTab === "demographic-data-tab" && <DemographicData selectedCustomerDemographicDataData={selectedCustomerData.demographicData}/>}
        {activeTab === "mail-address-tab" && <MailAddress selectedCustomerMailAddressData={selectedCustomerData.mailAddress}/>}
        {activeTab === "phone-number-tab" && <PhoneNumber selectedCustomerPhoneNumberData={selectedCustomerData.phoneNumber}/>}
        {activeTab === "email-address-tab" && <EmailAddress selectedCustomerEmailAddressData={selectedCustomerData.emailAddress}/>}
        {activeTab === "national-identity-tab" && <NationalIdentity selectedCustomerNationalIdentityData={selectedCustomerData.nationalIdentity}/>}
      </div>
    </div>
  );
};

export default CustomerDetail;
