import axios from 'axios';
import React, { useEffect, useState } from 'react';

type UserPanelProps = {
  user: {
    token: string;
    tokenType: string;
  };
};

type UserData = {
  name: string;
  organization: string;
  photoUrl?: string;
};

const UserPanel: React.FC<UserPanelProps> = ({ user }) => {
  const [userData, setUserData] = useState<UserData | null>(null);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get('/api/v1/users/me', {
          headers: {
            Authorization: `${user.tokenType} ${user.token}`,
          },
        });
        setUserData(response.data);
      } catch (error) {
        console.error('Error fetching user data', error);
      }
    };

    fetchUserData();
  }, [user]);

  if (!userData) return null;

  return (
    <div className="user-panel">
      <img src={userData.photoUrl || '/default-avatar.png'} alt="Avatar" className="user-photo" />
      <div className="user-info">
        <strong>{userData.name}</strong>
        <p>{userData.organization}</p>
      </div>
    </div>
  );
};

export default UserPanel;
