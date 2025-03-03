import { useState } from "react";

const FormularioTopico = () => {
  const [form, setForm] = useState({
    titulo: "",
    mensagem: "",
    imagem: "",
    idAssunto: "1",
  });

  const opcoesAssunto = [
    { nome: "Mecânica Geral", value: 1 },
    { nome: "Termologia", value: 2 },
    { nome: "Mecânica do fluidos", value: 3 },
    { nome: "Eletricidade e Magnetismo", value: 4 },
    { nome: "Óptica", value: 5 },
    { nome: "Ondulatório e Movimentos Periódicos", value: 6 },
    { nome: "Física Moderna", value: 7 },
    { nome: "Matemática do Ensino Fundamental", value: 8 },
    { nome: "Álgebra", value: 9 },
    { nome: "Matemática Financeira", value: 10 },
    { nome: "Probabilidades, Estatística e Análise Combinatória", value: 11 },
    { nome: "Trigonometria", value: 12 },
    { nome: "Geometria Plana e Espacial", value: 13 },
    { nome: "Geometria Analítica", value: 14 },
    { nome: "Iniciação ao Cálculo", value: 15 },
    { nome: "Química geral e inorgânica", value: 16 },
    { nome: "Química Orgânica", value: 17 },
    { nome: "Comunicados", value: 18 },
    { nome: "Instruções de utilização", value: 19 },
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = JSON.parse(localStorage.getItem('user-info')).token;

    try {
      const response = await fetch("http://localhost:8080/auth/criar-topico", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
          titulo: form.titulo,
          mensagem: form.mensagem,
          imagem: form.imagem.trim() || null,
          idAssunto: form.idAssunto
        })
      });

      if (!response.ok) {
        throw new Error("Erro ao enviar o formulário");
      }

      alert("Tópico criado com sucesso!");
      setForm({ titulo: "", mensagem: "", imagem: "", idAssunto: "1" });
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="formulario_topico">
      <label>Título:</label>
        <input type="text" name="titulo" value={form.titulo} onChange={handleChange} required />
      <br />
      <label>Mensagem:</label>
        <textarea name="mensagem" value={form.mensagem} onChange={handleChange} rows="20" cols="100"  required />
      <br />
      <label>Link da Imagem {"(opcional)"}:</label>
        <input type="url" name="imagem" value={form.imagem} onChange={handleChange} />
      <br />
      <label>Assunto:</label>
        <select name="idAssunto" value={form.idAssunto} onChange={handleChange} required>
          {opcoesAssunto.map((opcao) => (
            <option key={opcao.value} value={opcao.value}>
              {opcao.nome}
            </option>
          ))}
        </select>
      
      <br />
      <button type="submit">Criar Tópico</button>
    </form>
  );
};

export default FormularioTopico;
