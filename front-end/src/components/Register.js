import React, { useState } from "react";

function Register() {

    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("");

        const formData = new FormData(e.target);
        const data = Object.fromEntries(formData.entries());
        const response = await fetch("http://localhost:8080/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        });

        const messageApi = await response.text();

        setMessage(messageApi)
    };

    return (
        <div>
            <form className="form_registro" onSubmit={handleSubmit}>
                <p>Preencha o formulário</p>

                <div className="entrada_form_registro">
                    <label htmlFor="username">Nome de usuário: </label>
                    <input type="text" id="username" name="username" required />
                </div>

                <div className="entrada_form_registro">
                    <label htmlFor="email">Endereço de e-mail: </label>
                    <input type="email" id="email" name="email" required />
                </div>

                <div className="entrada_form_registro">
                    <label htmlFor="password">Senha: </label>
                    <input type="password" id="password" name="password" required />
                </div>

                <input type="submit" value="Registrar" />
            </form>

            {message}
        </div>
    );
}

export default Register;
