import React, { useEffect, useState } from "react";

const Assuntos = () => {
  const [assuntos, setAssuntos] = useState([]);

  useEffect(() => {
    // Função para buscar os dados do servidor
    const fetchAssuntos = async () => {
      try {
        const response = await fetch("http://localhost:8080/assuntos");
        const data = await response.json();
        setAssuntos(data);
      } catch (error) {
        console.error("Erro ao buscar os dados:", error);
      }
    };

    fetchAssuntos();
  }, []);

  // Agrupar assuntos por disciplina
  const agruparPorDisciplina = (dados) => {
    return dados.reduce((acc, assunto) => {
      const { disciplina } = assunto;
      if (!acc[disciplina]) {
        acc[disciplina] = [];
      }
      acc[disciplina].push(assunto);
      return acc;
    }, {});
  };

  const assuntosAgrupados = agruparPorDisciplina(assuntos);

  return (
    <div>
      {Object.entries(assuntosAgrupados).map(([disciplina, assuntos]) => (
        <div key={disciplina} className="assunto">
          <h2>{disciplina}</h2>
          <ul>
            {assuntos.map((assunto) => (
              <li key={assunto.id}>
                <a href={`http://localhost:3000/topicos/assunto/${assunto.id}`}>
                  {assunto.nome}
                </a>
                <p>
                    {assunto.descricao}
                </p>
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default Assuntos;
