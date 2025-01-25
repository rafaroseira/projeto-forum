import React, { useState } from "react";

function Registro() {

    const [mensagem, setMensagem] = useState(""); // Estado para mensagens
    const [tipoMensagem, setTipoMensagem] = useState(""); // "sucesso" ou "erro"

    const handleSubmit = async (e) => {
        e.preventDefault(); // Evita o comportamento padrão do formulário
        setMensagem(""); // Limpa a mensagem anterior

        const formData = new FormData(e.target);
        const data = Object.fromEntries(formData.entries());

        try {
            const response = await fetch("http://localhost:8080/cadastrar", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            const mensagemApi = await response.text(); // Obtém a mensagem do servidor

            if (response.ok) {
                setMensagem(mensagemApi);
                setTipoMensagem("sucesso");
            } else {
                setMensagem(mensagemApi);
                setTipoMensagem("erro");
            }
        } catch (error) {
            setMensagem("Erro ao conectar com o servidor.");
            setTipoMensagem("erro");
        }
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
                    <label htmlFor="senha">Senha: </label>
                    <input type="password" id="senha" name="senha" required />
                </div>

                <input type="submit" value="Registrar" />
            </form>

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
        </div>
    );
}

export default Registro;
