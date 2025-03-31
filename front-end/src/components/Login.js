import React, { useState } from "react";
import { useAuth } from "./Auth";
import { useNavigate } from "react-router-dom";

function Login(){

    const auth = useAuth();
    const navigate = useNavigate();
    const [mensagem, setMensagem] = useState("");
    const handleSubmit = async (e) => {
        e.preventDefault();
        setMensagem("");

        const formData = new FormData(e.target);
        const data = Object.fromEntries(formData.entries());

        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            const msg = await response.text();

            if (response.ok) {
                auth.login(msg);
                navigate("/", {replace:true});
            } else {
                setMensagem(msg);
            }

        } catch (error) {
            console.log("Erro ao conectar com o servidor.", error);
        }
    };

    return(
        <form className="form_login" onSubmit={handleSubmit}>
            <p>Entrar</p>

            <div className="entrada_form_login">
                <label htmlFor="username">Nome de usuário: </label>
                <input type="text" id="username" name="username" required/>
            </div>

            <div className="entrada_form_login">
                <label htmlFor="password">Senha: </label>
                <input type="password" id="password" name="password" required/>
            </div>

            <input type="submit" value="Entrar"/>

            {mensagem}
        </form>

    );
}

export default Login;