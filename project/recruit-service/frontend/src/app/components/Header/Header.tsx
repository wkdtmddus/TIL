import styles from "./Header.module.css"
import TopLeftButton from "../Button/TopLeftButton";
import TopRightButton from "../Button/TopRightButton";



interface HeaderProps {
    imagesSrc?: string; // Make this optional
    imageRightSrc?: string; // Make this optional
    altText: string;
    altTextRight?: string;
    href: string;
    hrefRight?: string;
    navigateType: 'replace' | 'push' | 'not';
    navigateTypeRight?: 'replace' | 'push';
    title?: string; // Make title optional
    onClick?: () => void; // Optional additional onClick event handler
    showTopLeftButton?: boolean; 
    showTopRightButton?: boolean; // Control the visibility of the TopRightButton
    onModify?: () => void; // Make optional
    onRequestCancel?: () => void; // Make optional
}

const Header: React.FC<HeaderProps> = ({
    imagesSrc,
    imageRightSrc,
    altText,
    href, 
    navigateType,
    title,
    onClick,
    showTopLeftButton = true,
    showTopRightButton = true,
    onModify,
    onRequestCancel
}) => {
    return (
        <div className={styles.header}>
            {showTopLeftButton && (
                <div className={styles.backicon}>
                    <TopLeftButton
                        imageSrc={imagesSrc|| ''}
                        altText={altText}
                        href={href}
                        navigateType={navigateType}
                        onClick={onClick}
                    />
                </div>
            )}
            <h2 className={`text-xlarge ${styles.headertitle}`}>{title}</h2>
            {showTopRightButton && (
                <div className={styles.threedotsicon}>
                    <TopRightButton
                        imageRightSrc={imageRightSrc|| ''}
                        altText={altText}
                        onModify={onModify ? onModify : () => {}} // Fallback to a noop if not provided
                        onRequestCancel={onRequestCancel ? onRequestCancel : () => {}} // Fallback to a noop if not provided
                    />
                </div>
            )}
        </div>
    );
}
export default Header;

