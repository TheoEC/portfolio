import React, { useEffect, useState, memo } from 'react';
import { SiTruenas } from 'react-icons/si';

// Componente memoizado para evitar re-renderizações desnecessárias
// const MemoizedImage = memo(({ src, alt }) => (
//   <img
//     src={src}
//     alt={alt}
//     style={{ width: '100%', display: 'block' }}
//   />
// ));
const MemoizedMedia = memo(({ src, style, alt }) => {
  if (src.endsWith('.webm')) {
    return (
      <video
        src={src}
        autoPlay
        loop
        muted
        style={{ width: '100%', display: 'flex' }}
      />
    );
  }
  return (
    <img
      src={src}
      alt={alt}
      style={{ width: '100%', display: 'flex' }}
    />
  );
});

const Carousel = ({ staticImages, gifImages = [], nextApp }) => {
  // Usamos 100% como largura, então as medidas serão em porcentagem.
  // O translateX será calculado como -nextApp * 100 (em %).
  const [scale, setScale] = useState(1);
  const [translateX, setTranslateX] = useState(0); // valor em %
  const [activeGifIndex, setActiveGifIndex] = useState(null); // null: usa imagem estática

  useEffect(() => {
    if (activeGifIndex !== null) {
      setActiveGifIndex(null);
      setScale(0.8);

      const timeoutMove = setTimeout(() => {
        setTranslateX(-nextApp * 100); // move -100% por índice
      }, 300);

      const timeoutFinish = setTimeout(() => {
        setScale(1);
        setActiveGifIndex(nextApp);
      }, 800);

      return () => {
        clearTimeout(timeoutMove);
        clearTimeout(timeoutFinish);
      };
    } else {
      setActiveGifIndex(nextApp);
    }
  }, [nextApp]);

  return (
    <div
      style={{
        overflow: 'hidden',
        width: '100%',
        willChange: 'transform',
        transform: 'translateZ(0)', // aceleração de hardware
      }}
    >
      <div
        style={{
          display: 'flex',
          transform: `translateX(${translateX}%) translateZ(0)`,
          transition: 'transform 0.5s',
          willChange: 'transform',
        }}
      >
        {staticImages.map((staticSrc, idx) => {
          const src =
            activeGifIndex === idx && gifImages[idx]
              ? gifImages[idx]
              : staticSrc;
          return (
            <div
              key={idx}
              style={{
                display: 'flex',
                flexDirection: 'row',
                width: '100%', // cada slide ocupa 100% da largura do container
                flexShrink: 0, // evita encolhimento
              }}
            >
              <div
                style={{
                  display: 'flex',
                  width: '100%',
                  transform: `scale(${scale})`,
                  transition: 'transform 0.3s',
                }}
              >
                <MemoizedMedia
                  src={src}
                  alt={`imagem-${idx}`}
                />
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default Carousel;