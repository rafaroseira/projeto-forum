import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "./Auth";

function Navbar() {
  const navigate = useNavigate();
  const auth = useAuth();

  const handleLogout = () => {
    auth.logout();
    navigate('/');
  }

  return (
    <div className="navbar">
      <ul className="links">
        <li><Link to="/">Início</Link></li>
        <li><Link to="/regras">Regras</Link></li>
        {auth.user != null ? (
          <>
            <li><Link to="/meus-topicos">Meus Tópicos</Link></li>
            <li><Link to="/criar-topico">Criar Tópico</Link></li>
            <button onClick={handleLogout} className="botao sair">Sair</button>
          </>
        ) : (
          <>
            <li><Link to="/entrar">Entrar</Link></li>
            <li><Link to="/registrar">Registrar</Link></li>
          </>
        )}
      </ul>
    </div>
  );
}

export default Navbar;
