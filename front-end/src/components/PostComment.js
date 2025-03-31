import React, { useState } from 'react';

const PostarComentario = ({topic}) => {
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        const payload = {
            topicId: topic,
            message: message
        };

        try {
            const response = await fetch('http://localhost:8080/topics/post-comment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('user')}`
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error('Erro ao enviar o comentário.');
            }

            setMessage('');
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className='postar_comentario'>
            <form onSubmit={handleSubmit}>
                <div> 
                    <textarea
                        placeholder='Escreva um comentário'
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}
                        rows="10"
                        cols="50"
                        name='message'
                        required
                    ></textarea>
                </div>

                {error && <p style={{ color: 'red' }}>{error}</p>}

                <button type="submit" className='botao enviar'>Enviar</button>
            </form>
        </div>
    );
};

export default PostarComentario;
