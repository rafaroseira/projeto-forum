import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

function PreviewTopicos() {
  const { id } = useParams(); // Obtem o ID da URL
  const [topicos, setTopicos] = useState([]);

  useEffect(() => {
    // Faz a requisição ao endpoint
    fetch(`http://localhost:8080/topicos/assunto/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Erro ao buscar os tópicos");
        }
        return response.json();
      })
      .then((data) => setTopicos(data))
      .catch((error) => console.error("Erro:", error));
  }, [id]);

  return (
    <div className="topicos">
      <h2>Tópicos</h2>
      <ul>
        {topicos.length > 0 ? (
          topicos.map((topico) => (
            <li key={topico.id}>
              <Link to={`/topico/${topico.id}`}>
                {topico.titulo}
              </Link>
              <p>por  <b>{topico.usuario}</b></p>
            </li>
          ))
        ) : (
          <p>Nenhum tópico encontrado para este assunto.</p>
        )}
      </ul>
    </div>
  );
}

export default PreviewTopicos;
