import { useNavigate } from "react-router-dom";
import {useEffect, useState} from "react";

function TabelaUsuario({tipo}){
    const [topicos, setTopicos] = useState([]);

    const navigate = useNavigate();
    const navegarParaTopico = (id) => {
        navigate(`/topico/${id}`);
    }

    useEffect(() => {
        const verificarRole = () => {
            const role = JSON.parse(localStorage.getItem('user-info')).role;
            if(tipo !== 1 && role !== "ROLE_ADMIN"){
                navigate("/");
            }
        }

        const token = JSON.parse(localStorage.getItem('user-info')).token;

        const url = tipo === 1 ? "http://localhost:8080/auth/meus-topicos" : "http://localhost:8080/admin/todos-os-topicos"

        const fetchTopicos = async () =>{
            try {
                const response = await fetch(url, {
                    method: "GET",
                    headers: {
                       "Authorization": `Bearer ${token}`,
                        "Content-Type": "application/json" 
                    }
                });

                const data = await response.json();
                setTopicos(data);
              } catch (error) {
                console.error("Erro ao buscar os dados:", error);
              }
        }
        verificarRole();
        fetchTopicos();
    }, [])

    const excluirTopico = async (id) => {
        const token = JSON.parse(localStorage.getItem('user-info')).token;

        const response = await fetch(`http://localhost:8080/auth/excluir-topico/${id}`, {
            method: "DELETE",
            headers: {
              "Authorization": `Bearer ${token}`,
              "Content-Type": "application/json"
            },
        });

        alert(await response.text());
        setTopicos((prevTopicos) => prevTopicos.filter(topico => topico.id !== id));
    }

    return(
        <div>
        {topicos.length > 0 ? (
        
        <table className="tabela">
            <thead>
                {tipo === 1 && <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Assunto</th>
                    <th>Opções</th>
                </tr>}

                {tipo === 2 && <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Assunto</th>
                    <th>Usuário</th>
                    <th>Opções</th>
                </tr>}
            </thead>
            <tbody>
                {topicos.map((topico) => (
                <tr key={topico.id}>
                    <td>{topico.id}</td>
                    <td>{topico.titulo}</td>
                    <td>{topico.assunto}</td>
                    {tipo === 2 && <td>{topico.usuario}</td>}
                    <td>
                    <button className="botao visualizar" onClick={() => navegarParaTopico(topico.id)}>Visualizar</button>
                    <button className="botao excluir" onClick={() => excluirTopico(topico.id)}>Excluir</button>
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

export default TabelaUsuario;