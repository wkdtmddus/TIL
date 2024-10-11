'use client';

import { useEffect, useState } from 'react';
import api from '../../../pages/api/api';
import { EventSourcePolyfill } from 'event-source-polyfill';
import styles from './NotificationComponent.module.css';

interface Notification {
    senderNickname: string;
    contents: string;
    createdAt: string;
}

const NotificationComponent = () => {
    const [notifications, setNotifications] = useState<Notification[]>([]);
    const [notificationCount, setNotificationCount] = useState<number>(0);
    const [isVisible, setIsVisible] = useState<boolean>(false);
    const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;
    const accessToken = typeof window !== 'undefined' ? localStorage.getItem('Authorization') : null;

    useEffect(() => {
        if (!baseURL || !accessToken) {
            console.error('Base URL or Access Token is not defined');
            return;
        }

        const eventSource = new EventSourcePolyfill(
            `${baseURL}/notifications/subscribe`,
            {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                },
            }
        );

        eventSource.addEventListener('CONNECT', () => {
            console.log('Connected to SSE');
        });

        eventSource.addEventListener('CONTRACT_SUCCESS', (event) => {
            const newNotification: Notification = JSON.parse((event as MessageEvent).data);
            setNotifications((prev) => [...prev, newNotification]);
            setIsVisible(true);

            // 일정 시간 후에 알림을 숨기도록 설정 (예: 3초)
            setTimeout(() => {
                setIsVisible(false);
            }, 5000);
        });

        eventSource.addEventListener('NOTIFICATION_COUNT', (event) => {
            const count = Number((event as MessageEvent).data);
            console.log(count);
            setNotificationCount(count);
        });

        eventSource.addEventListener('ALERT_TEST', (event) => {
            const alertCount = Number((event as MessageEvent).data);
            console.log(alertCount);
            setNotificationCount(alertCount);
            setIsVisible(true);

            // 일정 시간 후에 알림을 숨기도록 설정 (예: 3초)
            setTimeout(() => {
                setIsVisible(false);
            }, 5000);
        });

        eventSource.onerror = (error) => {
            console.error('SSE Error:', error);
            eventSource.close();
        };

        return () => {
            eventSource.close();
        };
    }, [baseURL, accessToken]);

    const Test = async () => {


        // const newNotification: Notification = {
        //     senderNickname: 'TestUser',
        //     contents: 'This is a test notification.',
        //     createdAt: new Date().toLocaleTimeString(),
        // };

        // setNotifications((prev) => [...prev, newNotification]);
        // setNotificationCount((prev) => prev + 1);
        // setIsVisible(true);

        // // 일정 시간 후에 알림을 숨기도록 설정 (예: 3초)
        // setTimeout(() => {
        //     setIsVisible(false);
        // }, 5000);


        try {
            const response = await api.get(`${baseURL}/notifications/receive-test`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });
            console.log('알람 성공:', response.data);
        } catch (error) {
            console.error('알람 실패:', error);
        }
    };

    return (
        <div>
            {/* <button onClick={Test} className={styles.testbutton}>알림 테스트</button> */}
            {/* 알림이 표시될 때만 보이도록 설정 */}
            {isVisible && (
                <div className={styles.notificationPopup}>
                    <div className={styles.header}>📢알림📢</div>
                    {notifications.map((notification, index) => (
                        <div key={index} >
                            <div className={styles.notificationHeader}>
                                <div className={styles.senderInfo}>
                                    <div>
                                        {/* <div className={styles.senderIcon} /> */}
                                        <p><strong>{notification.senderNickname}</strong></p>
                                    </div>
                                    <div>
                                        <span className={styles.notificationTime}>{notification.createdAt}</span>
                                    </div>
                                </div>

                                <div className={styles.notificationContent}>
                                    {/* <div className={styles.notificationTitle}>New Notification</div> */}
                                    <p>{notification.contents}</p>
                                </div>
                            </div>
                            {/* {notification.senderNickname}: {notification.contents} at {notification.createdAt} */}
                        </div>
                        
                    ))}
                </div>
            )}
        </div>
    );
};

export default NotificationComponent;
