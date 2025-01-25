function CommentList({comentarios}) {

  return(
    <div className="lista_comentarios">
      <h1 style={{marginTop:"50px"}}>Comentários</h1>
      {comentarios.map((comment) => (
        <div className="comentario" key={comment.id}>
          <p>por <strong>{comment.usuario}</strong></p>
          <p style={{whiteSpace:"pre-line"}}>{comment.mensagem}</p>
          {comment.imagem != null && <img src={comment.imagem} alt="imagem" style={{maxWidth: "300px", margin: "10px", display:"block"}}/>}
        </div>
      ))}
    </div>
  )
}

export default CommentList;



  /*#####################################
  
  const [comments, setComments] = useState([]);

  function buildCommentTree(comments) {
    const commentMap = new Map();
  
    // Organiza os comentários em um mapa por ID
    comments.forEach((comment) => {
      commentMap.set(comment.id, { ...comment, replies: [] });
    });
  
    const rootComments = [];
  
    // Monta a árvore de comentários
    comments.forEach((comment) => {
      if (comment.resposta === 0) {
        // Comentário raiz
        rootComments.push(commentMap.get(comment.id));
      } else {
        // Adiciona como resposta ao comentário pai
        const parent = commentMap.get(comment.resposta);
        if (parent) {
          parent.replies.push(commentMap.get(comment.id));
        }
      }
    });
  
    return rootComments;
  }  

  useEffect(() => {
    // Fetch do endpoint da API
    fetch(`http://localhost:8080/topicos/${topicId}`)
      .then((response) => response.json())
      .then((data) => {
        if (data.comentarios) {
          // Constrói a árvore de comentários
          setComments(buildCommentTree(data.comentarios));
        }
      })
      .catch((error) => console.error("Erro ao carregar comentários:", error));
  }, [topicId]);

  return (
    <div>
      <h2>Comentários</h2>
      {comments.map((comment) => (
        <Comment key={comment.id} comment={comment} />
      ))}
    </div>
  );
  
  #######################################
  */