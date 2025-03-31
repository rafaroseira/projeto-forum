function CommentList({comments}) {

  return(
    <div className="lista_comentarios">
      <h1 style={{marginTop:"50px"}}>Comentários</h1>
      {comments.map((comment) => (
        <div className="comentario" key={comment.id}>
          <p>por <strong>{comment.user}</strong></p>
          <p style={{whiteSpace:"pre-line"}}>{comment.message}</p>
        </div>
      ))}
    </div>
  )
}

export default CommentList;