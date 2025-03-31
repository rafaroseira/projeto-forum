import { useState } from "react";

const CreateTopicForm = () => {
  const [form, setForm] = useState({
    title: "",
    message: "",
    categoryId: 1,
  });

  const categoryOptions = [
    { name: "Mecânica Geral", value: 1 },
    { name: "Termologia", value: 2 },
    { name: "Mecânica do fluidos", value: 3 },
    { name: "Eletricidade e Magnetismo", value: 4 },
    { name: "Óptica", value: 5 },
    { name: "Ondulatório e Movimentos Periódicos", value: 6 },
    { name: "Física Moderna", value: 7 },
    { name: "Matemática do Ensino Fundamental", value: 8 },
    { name: "Álgebra", value: 9 },
    { name: "Matemática Financeira", value: 10 },
    { name: "Probabilidades, Estatística e Análise Combinatória", value: 11 },
    { name: "Trigonometria", value: 12 },
    { name: "Geometria Plana e Espacial", value: 13 },
    { name: "Geometria Analítica", value: 14 },
    { name: "Iniciação ao Cálculo", value: 15 },
    { name: "Química geral e inorgânica", value: 16 },
    { name: "Química Orgânica", value: 17 },
    { name: "Comunicados", value: 18 },
    { name: "Instruções de utilização", value: 19 },
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('user');

    try {
      const response = await fetch("http://localhost:8080/topics/create-topic", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
          title: form.title,
          message: form.message,
          categoryId: Number(form.categoryId)
        })
      });

      if (!response.ok) {
        throw new Error("Erro ao enviar o formulário");
      }

      alert("Tópico criado com sucesso!");
      setForm({ title: "", message: "", categoryId: 1 });
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="formulario_topico">
      <label>Título:</label>
        <input type="text" name="title" value={form.title} onChange={handleChange} required />
      <br />
      <label>Mensagem:</label>
        <textarea name="message" value={form.message} onChange={handleChange} rows="20" cols="100"  required />
      <br />
      <label>Categoria:</label>
        <select name="categoryId" value={form.categoryId} onChange={handleChange} required>
          {categoryOptions.map((option) => (
            <option key={option.value} value={option.value}>
              {option.name}
            </option>
          ))}
        </select>
      
      <br />
      <button type="submit">Criar Tópico</button>
    </form>
  );
};

export default CreateTopicForm;
