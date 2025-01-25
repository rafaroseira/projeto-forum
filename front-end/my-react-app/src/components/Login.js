import React, { useState } from "react";
import { useAuth } from "./Auth";
import { useNavigate } from "react-router-dom";

function Login(){

    const auth = useAuth();
    const navigate = useNavigate();
    const [mensagem, setMensagem] = useState(""); // Estado para mensagens
    const [tipoMensagem, setTipoMensagem] = useState(""); // "sucesso" ou "erro"
    const handleSubmit = async (e) => {
        e.preventDefault(); // Evita o comportamento padrão do formulário
        setMensagem(""); // Limpa a mensagem anterior

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

            if (response.ok) {
                const user = await response.json();
                auth.login(user);
                navigate("/");
                setMensagem("Login efetuado com sucesso!");
                setTipoMensagem("sucesso");
            } else {
                setMensagem("Falha ao efetuar login!");
                setTipoMensagem("erro");
            }



        } catch (error) {
            setMensagem("Erro ao conectar com o servidor.");
            setTipoMensagem("erro");
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
                <label htmlFor="senha">Senha: </label>
                <input type="password" id="senha" name="senha" required/>
            </div>

            <input type="submit" value="Entrar"/>

            {mensagem && (
                <div
                    style={{
                        color: tipoMensagem === "sucesso" ? "green" : "red",
                        marginTop: "10px",
                    }}
                >
                    {mensagem}
                </div>
            )}
        </form>

    );
}

export default Login;