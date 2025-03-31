import { useNavigate } from "react-router-dom";
import {useEffect, useState} from "react";

function MyTopics(){
    const [topics, setTopics] = useState([]);

    const navigate = useNavigate();
    const navigateToTopic = (id) => {
        navigate(`/topico/${id}`);
    }

    useEffect(() => {

        const token = localStorage.getItem('user');

        const fetchTopics = async () =>{
            try {
                const response = await fetch("http://localhost:8080/topics/my-topics", {
                    method: "GET",
                    headers: {
                       "Authorization": `Bearer ${token}`,
                        "Content-Type": "application/json" 
                    }
                });

                const data = await response.json();
                setTopics(data);
              } catch (error) {
                console.error("Erro ao buscar os dados:", error);
              }
        }
        fetchTopics();
    }, [])

    const deleteTopic = async (id) => {
        const token = localStorage.getItem('user');

        const response = await fetch(`http://localhost:8080/topics/${id}`, {
            method: "DELETE",
            headers: {
              "Authorization": `Bearer ${token}`,
              "Content-Type": "application/json"
            },
        });

        alert(await response.text());
        setTopics((prevTopics) => prevTopics.filter(topic => topic.id !== id));
    }

    return(
        <div>
        {topics.length > 0 ? (
        
        <table className="tabela">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Categoria</th>
                    <th>Opções</th>
                </tr>

            </thead>
            <tbody>
                {topics.map((topic) => (
                <tr key={topic.id}>
                    <td>{topic.id}</td>
                    <td>{topic.title}</td>
                    <td>{topic.category}</td>
                    <td>
                    <button className="botao visualizar" onClick={() => navigateToTopic(topic.id)}>Visualizar</button>
                    <button className="botao excluir" onClick={() => deleteTopic(topic.id)}>Excluir</button>
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

export default MyTopics;