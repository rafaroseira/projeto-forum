function Login(){
    return(
        <form className="form_login">
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
        </form>
    );
}

export default Login;