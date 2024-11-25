import { useState } from "react";
import { UPDATE_DEMOGRAPHIC_DATA_URL } from "../utils/constants";

const DemographicData = (props) => {

  const [customerNumber, setCustomerNumber] = useState(props.selectedCustomerDemographicDataData.customerNumber);

  const [maritalStatus, setMaritalStatus] = useState(props.selectedCustomerDemographicDataData.maritalStatus);
  const [declaredAnnualIncome, setDeclaredAnnualIncome] = useState(props.selectedCustomerDemographicDataData.declaredAnnualIncome);
  const [occupation, setOccupation] = useState(props.selectedCustomerDemographicDataData.occupation);

  const [balanceOpeningDebt, setBalanceOpeningDebt] = useState(props.selectedCustomerDemographicDataData.balanceOpeningDebt);
  const [numberLoans, setNumberLoans] = useState(props.selectedCustomerDemographicDataData.numberLoans);
  const [numberCreditCards, setNumberCreditCards] = useState(props.selectedCustomerDemographicDataData.numberCreditCards);
  const [totalExistCreditLimit, setTotalExistCreditLimit] = useState(props.selectedCustomerDemographicDataData.totalExistCreditLimit);

  const updateDemographicData = async (customerNumber, maritalStatus, declaredAnnualIncome, occupation, 
    balanceOpeningDebt, numberLoans, numberCreditCards, totalExistCreditLimit) => {

    const response = await fetch(UPDATE_DEMOGRAPHIC_DATA_URL + customerNumber,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
        body: JSON.stringify({ 
          maritalStatus: maritalStatus,
          declaredAnnualIncome: declaredAnnualIncome,
          occupation: occupation,
          balanceOpeningDebt: balanceOpeningDebt,
          numberLoans: numberLoans,
          numberCreditCards: numberCreditCards,
          totalExistCreditLimit: totalExistCreditLimit
        })
      }
    );

    const status = response.status;

  };

  const handleUpdateDemographicData = () => {
      console.log("handleUpdateDemographicData");
      updateDemographicData(customerNumber, maritalStatus, declaredAnnualIncome, occupation, 
        balanceOpeningDebt, numberLoans, numberCreditCards, totalExistCreditLimit);
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
                  value={props.selectedCustomerDemographicDataData.customerNumber} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-6">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-marital-status">
                  Marital Status
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-marital-status"
                  type="text"
                  name="marital-status"
                  placeholder="Marital Status"
                  value={maritalStatus}
                  onChange={e => setMaritalStatus(e.target.value)} />
              </div>
              <div className="w-full md:w-1/2 px-3">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-occupation">
                  Occupation
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-occupation"
                  type="text"
                  name="occupation"
                  placeholder="Occupation"
                  value={occupation}
                  onChange={e => setOccupation(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-6">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-declared-annual-income">
                  Declared Annual Income
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-declared-annual-income"
                  type="text"
                  name="declared-annual-income"
                  placeholder="Declared Annual Income"
                  value={declaredAnnualIncome}
                  onChange={e => setDeclaredAnnualIncome(e.target.value)} />
              </div>
              <div className="w-full md:w-1/2 px-3">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-balance-opening-debt">
                  Balance Opening Debt
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-balance-opening-debt"
                  type="text"
                  name="balance-opening-debt"
                  placeholder="Balance Opening Debt"
                  value={balanceOpeningDebt}
                  onChange={e => setBalanceOpeningDebt(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-number-of-loans">
                  Number of Loans
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-number-of-loans"
                  type="text"
                  name="number-of-loans"
                  placeholder="Number of Loans"
                  value={numberLoans}
                  onChange={e => setNumberLoans(e.target.value)} />
              </div>
              <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-number-of-credit-cards">
                  Number of Credit Cards
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-number-of-credit-cards"
                  type="text"
                  name="number-of-credit-cards"
                  placeholder="Number of Credit Cards"
                  value={numberCreditCards}
                  onChange={e => setNumberCreditCards(e.target.value)} />
              </div>
              <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                <label
                  className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                  htmlFor="grid-total-existing-credit-limit">
                  Total Existing Credit Limit
                </label>
                <input
                  className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                  id="grid-total-existing-credit-limit"
                  type="text"
                  name="total-existing-credit-limit"
                  placeholder="Total Existing Credit Limit"
                  value={totalExistCreditLimit}
                  onChange={e => setTotalExistCreditLimit(e.target.value)} />
              </div>
            </div>
            <div className="flex flex-wrap -mx-3 mb-2">
              <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                <button
                  className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded"
                  type="submit"
                  onClick={(event) => handleUpdateDemographicData()}>
                  Update
                </button>
              </div>
            </div>
        </div>
      );
};

export default DemographicData;