import React, { useState, useEffect } from "react";
import {useParams} from "react-router-dom";
import CommentList from "./CommentList";
import PostarComentario from "./PostarComentario";
import { useAuth } from "./Auth";

const Topico = () => {
  const { id } = useParams();
  const [topic, setTopic] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const auth = useAuth();

  useEffect(() => {
    const fetchTopic = async () => {
      try {
        const response = await fetch(`http://localhost:8080/topico/${id}`);
        if (!response.ok) {
          throw new Error("Erro ao buscar os dados do tópico");
        }
        const data = await response.json();
        setTopic(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchTopic();
  }, [id]);

  if (loading) return <p>Carregando...</p>;
  if (error) return <p>Erro: {error}</p>;

  return (
    <div className="topico">
      <h1 style={{color:"green"}}>{topic.titulo}</h1>
      <p style={{fontSize:"12px"}}>por <strong>{topic.usuario}</strong></p>
      <p className="mensagem_topico">{topic.mensagem}</p>
      {topic.imagem != null && <img src={topic.imagem} alt="imagem" style={{maxWidth: "300px", margin: "10px", display:"block"}}/>}
      
      {auth.user && <PostarComentario topico={id}/>}

      <CommentList comentarios={topic.comentarios}/>
    </div>
  );
};

export default Topico;

/*{topic.imagens.map((imageUrl, index) => (
          <img 
            key={index}
            src={imageUrl}
            alt={`Imagem ${index + 1}`}
            style={{ maxWidth: "300px", margin: "10px", display:"block" }}
          />
        ))} */