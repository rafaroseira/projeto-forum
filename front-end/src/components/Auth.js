import { useState, useEffect, useContext, createContext } from "react";

// Criando o contexto de autenticação
const AuthContext = createContext(null);

const getUserInfo = () => {
    return localStorage.getItem("user") ? localStorage.getItem("user") : null;
}

export const AuthProvider = ({ children }) => {
    // Tentando recuperar o usuário armazenado no localStorage (se houver)
    const [user, setUser] = useState(getUserInfo());

    useEffect(() => {
        // Sempre que o usuário mudar, atualiza o localStorage
        if (user) {
            localStorage.setItem("user", user);
        } else {
            localStorage.removeItem("user");
        }
    }, [user]); // Atualiza sempre que o usuário mudar

    const login = (user) => {
        setUser(user); // Atualiza o estado com o novo usuário
    };

    const logout = () => {
        setUser(null); // Limpa o estado e remove o usuário do localStorage
    };

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

// Hook customizado para acessar o contexto de autenticação
export const useAuth = () => {
    return useContext(AuthContext);
};

