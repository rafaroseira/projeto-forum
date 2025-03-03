import {useEffect, useState} from "react";

function TabelaUsuarios(){
    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        const token = JSON.parse(localStorage.getItem('user-info')).token;

        const fetchUsuarios = async () => {

            try{
                const response = await fetch("http://localhost:8080/admin/todos-os-usuarios", {
                    method: "GET",
                    headers: {
                       "Authorization": `Bearer ${token}`,
                        "Content-Type": "application/json" 
                    }
                });

                const data = await response.json();
                setUsuarios(data);

            } catch(error) {
                console.log("Erro ao buscar dados", error);
            }
        }

        fetchUsuarios();
        
    },[])

    const excluirUsuario = async (id) => {
        const token = JSON.parse(localStorage.getItem('user-info')).token;

        const response = await fetch(`http://localhost:8080/admin/excluir-usuario/${id}`, {
            method: "DELETE",
            headers: {
              "Authorization": `Bearer ${token}`,
              "Content-Type": "application/json"
            },
        });

        alert(await response.text());
        setUsuarios((prevUsuarios) => prevUsuarios.filter(usuario => usuario.id !== id));
    }

    const tornarAdmin = async (id) => {
        const token = JSON.parse(localStorage.getItem('user-info')).token;

        const response = await fetch(`http://localhost:8080/admin/tornar-admin/${id}`, {
            method: "POST",
            headers: {
              "Authorization": `Bearer ${token}`,
              "Content-Type": "application/json"
            },
        });

        alert(await response.text());
        setUsuarios((prevUsuarios) => 
            prevUsuarios.map(usuario => 
                usuario.id === id ? { ...usuario, role: 'ROLE_ADMIN' } : usuario
            )
        );
    }

    return(
        <div>
        {usuarios.length > 0 ? (
        
        <table className="tabela">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Usuário</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                {usuarios.map((usuario) => (
                    
                <tr key={usuario.id}>
                    <td>{usuario.id}</td>
                    <td>{usuario.username}</td>
                    <td>
                        {
                            usuario.role === "ROLE_USER" ? (
                                <>
                                    <button className="botao visualizar" onClick={() => tornarAdmin(usuario.id)}>Tornar administrador</button>
                                </>
                            ) : (
                                <>
                                    <button className="botao visualizar" disabled>Já é um administrador</button>
                                </>
                            )
                        }
                        <button className="botao excluir" onClick={() => excluirUsuario(usuario.id)}>Excluir</button>
                    </td>
                </tr>
                ))}      
            </tbody>
        </table>) : (
            <p>Nenhum tópico criado</p>
        )}
        </div>
    );
}

export default TabelaUsuarios;