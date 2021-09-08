import React, { Component } from "react";
import { Col, Container, Row } from "react-bootstrap";
import translateCard from "./translateCard";

class ShowHand extends Component {
    render() {
        let { hand } = this.props;
        let cards = hand.cards;
        let playerName = this.props.playerName;
        let playerNumber = this.props.playerNumber;
        let pages = [];
        let names = [];
        let imageFileNames = [];
        if (cards.length === 0) {
            imageFileNames.push("i_win.png");
        }
        else {
            imageFileNames = cards.map(card =>
                translateCard(card.color, card.value));
        }
        console.log(imageFileNames);
        for (let i = 0; i < imageFileNames.length; i++) {
            let image = imageFileNames[i];
            pages.push(
                <Col key={i}>
                    <img
                        title={image}
                        src= {process.env.PUBLIC_URL + `/small_cards/${image}`}
                        alt={image}
                        style={{ height: "5rem" }}/>
                </Col>
            );
        }
names.push(
    <Col key={playerNumber}>
        {" "}
        <h3>{playerName}</h3>
    </Col>
);
return (
    <div style={{ textAlign: "center", margin: "10px"}}>
        <Container>
            <Row>{pages}</Row>
            <Row>{names}</Row>
        </Container>
    </div>
);
    }
}
export default ShowHand;