import React from 'react';
import styles from './MainCardLIst.module.css';
import '../typography.css';

interface CardProps {
  title: string;
  price: number;
  likes: number;
  views: number;
  date: string;
  location: string;
  recruitImgUrl: string;
}

const Card: React.FC<CardProps> = ({ title, price, likes, views, date, location, recruitImgUrl }) => {

  return (
    <div className={styles.card}>
      <div className={styles.textContainer}>
        <div className="text-large">{title}</div>
        <div className="text-medium">{price} 원</div>
        <div className="text-medium">♡ {likes} | {views}명 조회</div>
        <div className="text-small">{date}</div>
        <div className="text-small">{location}</div>
      </div>
      {recruitImgUrl ? (
        <img src={recruitImgUrl} alt="recruit" className={styles.image} />
      ) : (
        <img src="/image/MainCardList.png" alt="recruit" className={styles.image} />
      )}

    </div>
  );
};

export default Card;
