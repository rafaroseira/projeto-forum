import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

function PreviewTopics() {
  const { id } = useParams(); // Obtem o ID da URL
  const [topics, setTopics] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/topics/category/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Erro ao buscar os tópicos");
        }
        return response.json();
      })
      .then((data) => setTopics(data))
      .catch((error) => console.error("Erro:", error));
  }, [id]);

  return (
    <div className="topicos">
      <h2>Tópicos</h2>
      <ul>
        {topics.length > 0 ? (
          topics.map((topic) => (
            <li key={topic.id}>
              <Link to={`/topico/${topic.id}`}>
                {topic.title}
              </Link>
              <p>por  <b>{topic.user}</b></p>
            </li>
          ))
        ) : (
          <p>Nenhum tópico encontrado para este assunto.</p>
        )}
      </ul>
    </div>
  );
}

export default PreviewTopics;
