import React, { useState, useEffect } from "react";
import {useParams} from "react-router-dom";
import CommentList from "./CommentList";
import { useAuth } from "./Auth";
import PostComment from "./PostComment";

const Topic = () => {
  const { id } = useParams();
  const [topic, setTopic] = useState(null);
  const [loading, setLoading] = useState(true);
  const auth = useAuth();

  useEffect(() => {
    const fetchTopic = async () => {
      try {
        const response = await fetch(`http://localhost:8080/topics/${id}`);
        if (!response.ok) {
          throw new Error("Erro ao buscar os dados do tópico");
        }
        const data = await response.json();
        setTopic(data);
      } catch (error) {
        console.log(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchTopic();
  }, [id]);

  if (loading) return <p>Carregando...</p>;

  return (
    <div className="topico">
      <h1 style={{color:"green"}}>{topic.title}</h1>
      <p style={{fontSize:"12px"}}>por <strong>{topic.user}</strong></p>
      <p className="mensagem_topico">{topic.message}</p>

      {auth.user && <PostComment topic={id}/>}

      <CommentList comments={topic.comments}/>
    </div>
  );
};

export default Topic;