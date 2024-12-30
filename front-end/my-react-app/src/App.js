import Header from "./components/Header";
import Navbar from "./components/Navbar";
import Regras from "./components/Regras";
import Registro from "./components/Registro";
import Login from "./components/Login";
import Assuntos from "./components/Assuntos";
import PreviewTopicos from "./components/PreviewTopicos";
import {Route, Routes} from "react-router-dom";
import Topico from "./components/Topico";

function App() {
  return (
    <div className="App">
        <Header/>
        <Navbar/>
        <Routes>
          <Route path="/" element={<Assuntos/>}></Route>
          <Route path="/regras" element={<Regras/>}></Route>
          <Route path="/entrar" element={<Login/>}></Route>
          <Route path="/registrar" element={<Registro/>}></Route>
          <Route path="/topicos/assunto/:id" element={<PreviewTopicos/>}></Route>
          <Route path="/topico/:id" element={<Topico/>}></Route>
        </Routes>
      </div>
  );
}

export default App;
