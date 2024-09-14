import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UserProfile = ({ accessToken }) => {
    const [profile, setProfile] = useState(null);

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await axios.get('https://www.googleapis.com/oauth2/v3/userinfo', {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                });
                setProfile(response.data);
                // Save user information to local server
                saveUserToServer(response.data);
            } catch (error) {
                console.error('Error fetching user profile:', error);
            }
        };

        if (accessToken) {
            fetchProfile();
        }
    },[accessToken]);

    const saveUserToServer = async (userData) => {
        try {
            // Make a POST request to save user data to server
            await axios.post('http://localhost:8082/users', {
                firstName: userData.name,
                email: userData.email
            });
            console.log('User data saved successfully');
        } catch (error) {
            console.error('Error saving user data:', error);
        }
    };

    if (!profile) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>User Profile</h2>
            <p>Name: {profile.name}</p>
            <p>Email: {profile.email}</p>

        </div>
    );
};

export default UserProfile;
