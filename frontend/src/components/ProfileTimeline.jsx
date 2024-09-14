import React, { useEffect, useState } from 'react';
import axios from 'axios';
// import postImg1 from "../assets/images/page-img/p2.jpg";
// import postImg3 from "../assets/images/page-img/p4.jpg";
import profileImg from "../assets/images/profile.png"
import comment_icon from "../assets/images/speech-bubble.png"
import like_icon from "../assets/images/thumb-up.png"

function TimelineFeed() {
    const [meals, setMeals] = useState([]);
    const [likeCounts, setLikeCounts] = useState({});

    useEffect(() => {
        fetchMeals();
        fetchLikeCounts();
    }, []);

    const fetchMeals = () => {
        axios.get('http://localhost:8082/meals')
            .then(response => {
                setMeals(response.data);
            })
            .catch(error => {
                console.error('Error fetching meals:', error);
            });
    }

    const fetchLikeCounts = () => {
        axios.get('http://localhost:8082/counts')
            .then(response => {
                const countsMap = {};
                response.data.forEach(item => {
                    countsMap[meals.mealId] = item.likeCount;
                });
                setLikeCounts(countsMap);
            })
            .catch(error => {
                console.error('Error fetching like counts:', error);
            });
    }

    const handleLikeClick = (mealId) => {
        axios.post(`http://localhost:8082/users/1/meals/5`, { status: 'true' })
            .then(response => {
                fetchLikeCounts();
            })
            .catch(error => {
                console.error('Error liking meal:', error);
            });
    }

    return (
        <div>
            {Array.isArray(meals) && meals.length > 0 ? (
                meals.map(meal => (
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
                        {/*<img alt="profileImg" src={profileImg} style={{width:'40px',height:'40px',marginTop:'-485px'}}/>*/}
                        <h4 style={{fontSize:'15px',marginTop:'-480px',marginLeft:'10px'}}>test name</h4>

                        {/*<img alt="post-image" src={postImg3} style={{ width: '350px', height: '330px', borderRadius: '10px', marginRight: '20px',marginTop:'60px',marginLeft:'-80px' }} />*/}
                        <img alt="post-image" src={`data:image/jpeg;base64,${meal.imageData}`} style={{ width: '350px', height: '330px', borderRadius: '10px', marginRight: '20px', marginTop: '60px', marginLeft: '-80px' }} />

                        <div style={{marginLeft:'125px', textAlign: 'left'}}>

                            <h3 style={{marginLeft:'-125px',marginTop:'75px',font:'A'}}>{meal.name}</h3>
                            <p style={{marginLeft:'-125px',marginTop:'10px'}}>Meal Type  : {meal.mealType}</p>
                            <p style={{marginLeft:'-125px'}}>Ingredients : {meal.ingredients}</p>
                            <p style={{marginLeft:'-125px'}}>Portion : {meal.portion}</p>
                            <p style={{marginLeft:'-125px'}}>Recipes : {meal.recipes}</p>
                            <div style={{width:'330px',marginLeft:'-140px'}}>
                                <hr/>
                            </div>

                            <div>
                                <img alt="like" src={like_icon} style={{ width: '30px', height: '30px', marginLeft: '-100px',marginTop:'60px', cursor: 'pointer' }} onClick={() => handleLikeClick(meal.id)} />
                                <span style={{ marginLeft: '5px', marginTop: '20px' }}>{likeCounts[meal.id] || 0}</span>
                                <img alt="comment" src={comment_icon} style={{ width: '30px', height: '30px', marginLeft: '70px',marginTop:'65px', cursor: 'pointer' }} />
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

export default TimelineFeed;
