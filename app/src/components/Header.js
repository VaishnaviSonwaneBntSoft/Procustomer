import nimbuspaylogo from "../resources/NimbusPayLogo.jpg";
import {Link} from "react-router-dom";

const Header = () => {
  return (
    <div className="flex justify-between bg-blue-50 shadow-lg">
      <div className="logo-container">
        <img className="p-2 w-24" alt="NimbusPay Inc" src={nimbuspaylogo}></img>
      </div>
      <div className="flex items-center">
        <ul className="flex p-4 m-4">
            <li className="px-4 hover:bg-blue-100 rounded-xl"><Link to="/">Home</Link></li>
            <li className="px-4 hover:bg-blue-100 rounded-xl"><Link to="/customer">Customer</Link></li>
        </ul>
      </div>
    </div>
  );
};

export default Header;
