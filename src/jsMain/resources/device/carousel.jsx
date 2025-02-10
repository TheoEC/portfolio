import React, { useEffect, useState, memo } from 'react';

const animationTotalTime = 1000; // Tempo total de animação em milissegundos
const moveTime = animationTotalTime * 0.6; // 60% do tempo para mover o carrossel
const scaleTime = animationTotalTime * 0.2; // 40% do tempo para escalar

const MemoizedMedia2 = memo(({ src, alt, paused }) => {
  const videoRef = React.useRef(null);
  console.log(`reproduzindo... ${paused}`)

  useEffect(() => {
    console.log("entrou useEffect")
    if (videoRef.current) {
      console.log(`entrou check: ${paused}`)
      if (paused) {
        videoRef.current.pause();
      } else {
        videoRef.current.play().catch((error) => console.error("Erro ao reproduzir vídeo:", error));
      }
    }
  }, [paused]);

  if (src.endsWith('.webm')) {
    return (
      <video
        ref={videoRef}
        src={src}
        loop
        muted
        playsInline
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
  const [scale, setScale] = useState(1);
  const [translateX, setTranslateX] = useState(0);
  const [activeGifIndex, setActiveGifIndex] = useState(null);

  useEffect(() => {
    if (activeGifIndex !== null) {
      setActiveGifIndex(null);
      setScale(0.8);

      const timeoutMove = setTimeout(() => {
        setTranslateX(-nextApp * 100);
      }, scaleTime);

      const timeoutGrow = setTimeout(() => {
        setScale(1);
      }, (scaleTime + moveTime));

      const timeoutApp = setTimeout(() => {
        setActiveGifIndex(nextApp);
      }, animationTotalTime);

      return () => {
        clearTimeout(timeoutMove);
        clearTimeout(timeoutGrow);
        clearTimeout(timeoutApp)
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
          transition: `transform ${moveTime}ms`, // Controla a duração da transição
          willChange: 'transform',
        }}
      >
        {gifImages.map((appSrc, idx) => {
          const src = appSrc;
          return (
            <div
              key={idx}
              style={{
                display: 'flex',
                flexDirection: 'row',
                width: '100%',
                flexShrink: 0,
              }}
            >
              <div
                style={{
                  display: 'flex',
                  width: '100%',
                  transform: `scale(${scale})`,
                  transition: `transform ${scaleTime}ms`, // Controla a transição de escala
                }}
              >
                <MemoizedMedia2
                  src={src}
                  alt={`imagem-${idx}`}
                  paused = {activeGifIndex !== idx}
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