import React, { useEffect, useState } from 'react';
import axios from 'axios';
import profileImg from "../assets/images/profile.png"
import comment_icon from "../assets/images/speech-bubble.png"
import like_icon from "../assets/images/thumb-up.png"
import id from "vanillajs-datepicker/locales/id";

function MealFeed() {
    const [meals, setMeals] = useState([]);
    const [user, setUser] = useState(null);
    const userId = localStorage.getItem('userId');

    console.log(user)
    useEffect(() => {
        fetchMeals();
        fetchUser();
    }, []);

    const fetchUser = () => {
        axios.get(`http://localhost:8082/users/${userId}`)
            .then(response => {
                setUser(response.data); // Set user state with retrieved user data
            })
            .catch(error => {
                console.error('Error fetching user:', error);
            });
    }

    const fetchMeals = () => {

        axios.get('http://localhost:8082/meals')
            .then(response => {

                console.log("userName ")
                const mealsData = response.data;
                axios.get('http://localhost:8082/counts')
                    .then(likeCountsResponse => {
                        const likeCounts = likeCountsResponse.data;
                        const mealsWithLikeCounts = mealsData.map(meal => {
                            const likeCountObj = likeCounts.find(count => count.mealId === meal.id);
                            return {
                                ...meal,
                                likeCount: likeCountObj ? likeCountObj.likeCount : 0
                            };
                        });
                        setMeals(mealsWithLikeCounts);
                    })
                    .catch(error => {
                        console.error('Error fetching like counts:', error);
                    });
            })
            .catch(error => {
                console.error('Error fetching meals:', error);
            });
    }


    const handleLikeClick = (mealId, index) => {
        axios.post(`http://localhost:8082/users/${userId}/meals/1`, { status: true })
            .then(response => {
                const updatedMeals = [...meals];
                updatedMeals[index].likeCount += 1;
                setMeals(updatedMeals);

            })
            .catch(error => {
                console.error('Error liking meal:', error);
            });
    }

    return (
        <div>
            {meals.length > 0 ? (
                meals.map((meal, index) => (
                    <div
                        key={meal.id}
                        className="shadow mb-5 mt-3 bg-light rounded"
                        style={{
                            display: 'flex',
                            alignItems: 'normal',
                            width: '720px',
                            height: 'auto',
                            margin: '0 auto 20px auto',
                            padding: '20px',
                        }}
                    >
                        <img alt="profileImg" src={profileImg} style={{ width: '40px', height: '40px', marginTop: '-485px' }} />
                        <h4 style={{ fontSize: '18px', marginTop: '-480px', marginLeft: '10px' }}>{user.firstName+' '+user.lastName}</h4>

                        <img alt="post-image" src={`data:image/jpeg;base64,${meal.imageData}`} style={{ width: '250px', height: '250px', borderRadius: '10px', marginRight: '20px', marginTop: '60px', marginLeft: '-80px' }} />

                        <div style={{ marginLeft: '200px', textAlign: 'left' }}>

                            <h3 style={{ marginLeft: '-125px', marginTop: '75px', font: 'A' }}>{meal.name}</h3>
                            <p style={{ marginLeft: '-125px', marginTop: '10px' }}>Meal Type  : {meal.mealType}</p>
                            <p style={{ marginLeft: '-125px' }}>Ingredients : {meal.ingredients}</p>
                            <p style={{ marginLeft: '-125px' }}>Portion : {meal.portion}</p>
                            <p style={{ marginLeft: '-125px' }}>Recipes : {meal.recipes}</p>
                            <div style={{ width: '330px', marginLeft: '-140px' }}>
                                <hr />
                            </div>

                            <div>
                                <img alt="like" src={like_icon} style={{ width: '30px', height: '30px', marginLeft: '-100px', marginTop: '60px', cursor: 'pointer' }} onClick={() => handleLikeClick(meal.id, index)} />
                                <span style={{ marginLeft: '10px', marginTop: '65px' }}>{meal.likeCount} likes</span>
                                <img alt="comment" src={comment_icon} style={{ width: '30px', height: '30px', marginLeft: '20px', marginTop: '65px', cursor: 'pointer' }} />
                            </div>
                        </div>
                    </div>
                ))
            ) : (
                <p>No meals available</p>
            )}
        </div>
    );
}

export default MealFeed;
