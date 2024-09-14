import React, { useEffect, useState, useRef } from 'react';
import { Button, Dropdown, Modal } from 'react-bootstrap';
import axios from 'axios';
import SidebarLayout from '../components/SidebarLayout';
import { FaUpload } from 'react-icons/fa';
import { useParams } from 'react-router-dom';
import like_icon from "../assets/images/thumb-up.png";
import upload_icon from "../assets/images/upload-img.png"

function MealPlan() {
    const [name, setName] = useState('');
    const [mealType, setMealType] = useState('');
    const [ingredients, setIngredients] = useState('');
    const [portion, setPortion] = useState('');
    const [nutrition, setNutrition] = useState('');
    const [recipes, setRecipes] = useState('');
    const [instructions, setInstructions] = useState('');
    const [imageData, setImageData] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [userId, setUserId] = useState('');
    const fileInputRef = useRef(null);
    const { userId: paramUserId } = useParams();

    const [user, setUser] = useState([]);


    useEffect(() => {
        const userId = localStorage.getItem('userId');
        setUserId(paramUserId);
    }, [paramUserId]);




    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('name', name);
        formData.append('mealType', mealType);
        formData.append('ingredients', ingredients);
        formData.append('portion', portion);
        formData.append('nutrition', nutrition);
        formData.append('recipes', recipes);
        formData.append('instructions', instructions);
        formData.append('imageData', imageData);

        try {
            const userId = localStorage.getItem('userId');
            console.log(userId)
            const response = await axios.post(`http://localhost:8082/user/${userId}/meals`, formData, {

                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            console.log('Meal Plan submitted successfully:', response.data);
            setShowModal(true);
            // Optionally, you can reset the form fields here
            setName('');
            setMealType('');
            setIngredients('');
            setPortion('');
            setNutrition('');
            setRecipes('');
            setInstructions('');
            setImageData(null);
        } catch (error) {
            console.error('Error submitting meal plan:', error);
        }
    };

    const handleFileChange = (e) => {
        setImageData(e.target.files[0]);
    };

    const handleOpenFileDialog = () => {
        fileInputRef.current.click();
    };

    const handleCloseModal = () => {
        setShowModal(false);
    };

    return (
        <div className="App">
            <SidebarLayout />

            <div className="content p-5 " style={{ marginTop: '60px', marginLeft: '350px' }}>
                <div className="mb-5" style={{ marginRight: '260px', marginTop: '-10px' }}>
                    <h1>Create Meal Plan</h1>
                </div>
                <div className="card" style={{ background: '#EEEDED', height: '960px', width: '900px' }}>
                    <form onSubmit={handleSubmit}>
                        <div>
                            <input
                                type="file"
                                ref={fileInputRef}
                                style={{ display: 'none' }}
                                onChange={handleFileChange}
                                accept="image/*"
                            />
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>

                                {/*<img alt="like" src={upload_icon} style={{ width: '150px', height: '150px', marginLeft: '-1px',marginTop:'20px',marginBottom:'20px', cursor: 'pointer',borderStyle:'solid',borderRadius:'10px' }} onClick ={handleOpenFileDialog}/>*/}
                                <div style={{ position: 'relative' }}>
                                    {imageData ? (
                                        <img
                                            src={URL.createObjectURL(imageData)}
                                            alt="meal"
                                            style={{ width: '150px', height: '150px', marginLeft: '-1px', marginTop: '20px', marginBottom: '20px', cursor: 'pointer', borderStyle: 'solid', borderRadius: '10px' }}
                                            onClick={handleOpenFileDialog}
                                        />
                                    ) : (
                                        <img
                                            alt="like"
                                            src={upload_icon}
                                            style={{ width: '150px', height: '150px', marginLeft: '-1px', marginTop: '20px', marginBottom: '20px', cursor: 'pointer', borderStyle: 'solid', borderRadius: '10px' }}
                                            onClick={handleOpenFileDialog}
                                        />
                                    )}
                                </div>

                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px',marginBottom:"60px" }}>
                                <label className="fs-5" >Meal Name:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={name}
                                    style={{marginBottom : '20px'}}
                                    onChange={(e) => setName(e.target.value)}
                                    placeholder="name"
                                    required
                                />
                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>
                                <label className="fs-5" >Meal Type:</label>
                                <Dropdown style={{marginBottom : '20px'}} onSelect={(eventKey) => setMealType(eventKey)}>
                                    <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                                        {mealType ? mealType : 'Select Meal Type'}
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item eventKey="Breakfast">Breakfast</Dropdown.Item>
                                        <Dropdown.Item eventKey="Lunch">Lunch</Dropdown.Item>
                                        <Dropdown.Item eventKey="Dinner">Dinner</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>
                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>
                                <label className="fs-5" >Ingredients:</label>
                                <input
                                    style={{marginBottom : '20px'}}
                                    type="text"
                                    value={ingredients}
                                    className="form-control"
                                    placeholder="Ingredients"
                                    onChange={(e) => setIngredients(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>
                                <label className="fs-5" >Portion:</label>
                                <Dropdown style={{marginBottom : '20px'}} onSelect={(eventKey) => setPortion(eventKey)}>
                                    <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                                        {portion ? portion : 'Select Portion'}
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item eventKey="Small">Small</Dropdown.Item>
                                        <Dropdown.Item eventKey="Medium">Medium</Dropdown.Item>
                                        <Dropdown.Item eventKey="Large">Large</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>
                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>
                                <label className="fs-5">Nutrition:</label>
                                <input
                                    type="text"
                                    value={nutrition}
                                    style={{marginBottom : '20px'}}
                                    className="form-control"
                                    onChange={(e) => setNutrition(e.target.value)}
                                    placeholder="Nutrition"
                                    required
                                />
                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>
                                <label className="fs-5">Recipes:</label>
                                <input
                                    style={{marginBottom : '20px'}}
                                    type="text"
                                    className="form-control"
                                    placeholder="Recipes"
                                    value={recipes}
                                    onChange={(e) => setRecipes(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                        <div>
                            <div className="mb-2" style={{ marginLeft: '90px', marginRight: '90px' }}>
                                <label className="fs-5">Instructions:</label>
                                <input
                                    type="text"
                                    placeholder="Instructions"
                                    style={{marginBottom : '80px'}}
                                    value={instructions}
                                    className="form-control"
                                    onChange={(e) => setInstructions(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                        <div className="d-flex align-items-start mt-4" style={{ marginLeft: '250px', marginRight: '250px' }}>
                            <button type="submit" className="btn btn-primary" style={{ width: 750 }}>
                                Post
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <Modal show={showModal} onHide={handleCloseModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Meal added</Modal.Title>
                </Modal.Header>
                <Modal.Body>Your meal has been successfully added.</Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleCloseModal}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

export default MealPlan;