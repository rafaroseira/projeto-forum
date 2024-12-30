import { Link } from "react-router-dom";

function Navbar(){
    return(
        <div className="navbar">
            <ul className="links">
                <li><Link to="/">Início</Link></li>
                <li><Link to="/regras">Regras</Link></li>
                <li><Link to="/entrar">Entrar</Link></li>
                <li><Link to="/registrar">Registrar</Link></li>
            </ul>
        </div>
    );
}

export default Navbar;