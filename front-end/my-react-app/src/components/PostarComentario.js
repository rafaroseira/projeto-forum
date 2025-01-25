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
            imagem: imagem.trim() || null, // Se vazio, será enviado como null
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

            console.log(response.text());
            setMensagem('');
            setImagem('');
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div> 
                    <textarea
                        placeholder='Escreva um comentário'
                        value={mensagem}
                        onChange={(e) => setMensagem(e.target.value)}
                        rows="20"
                        cols="100"
                        name='mensagem'
                        required
                    ></textarea>
                </div>

                <div>
                    
                    <input
                        type="url"
                        value={imagem}
                        onChange={(e) => setImagem(e.target.value)}
                        placeholder="Link da imagem (opcional)"
                        name='imagem'
                    />
                </div>

                {error && <p style={{ color: 'red' }}>{error}</p>}

                <button type="submit">Enviar</button>
            </form>
        </div>
    );
};

export default PostarComentario;
