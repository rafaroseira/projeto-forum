import React, { useState } from 'react';

const PostarComentario = ({topico}) => {
    const [mensagem, setMensagem] = useState('');
    const [imagem, setImagem] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        if (!mensagem.trim()) {
            setError('O campo mensagem é obrigatório.');
            return;
        }

        const payload = {
            idTopico: topico,
            mensagem: mensagem,
            imagem: imagem.trim() || null,
        };

        try {
            const response = await fetch('http://localhost:8080/auth/criar-comentario', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${JSON.parse(localStorage.getItem('user-info')).token}`
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error('Erro ao enviar o comentário.');
            }

            setMensagem('');
            setImagem('');
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
                        value={mensagem}
                        onChange={(e) => setMensagem(e.target.value)}
                        rows="10"
                        cols="50"
                        name='mensagem'
                        required
                    ></textarea>
                </div>

                <div>
                    
                    <input
                        type="url"
                        value={imagem}
                        onChange={(e) => setImagem(e.target.value)}
                        placeholder="Adicione uma imagem ao comentário (opcional)"
                        name='imagem'
                    />
                </div>

                {error && <p style={{ color: 'red' }}>{error}</p>}

                <button type="submit" className='botao enviar'>Enviar</button>
            </form>
        </div>
    );
};

export default PostarComentario;
