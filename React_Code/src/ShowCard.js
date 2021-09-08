import React, { Component } from "react";
import FigureCaption from "react-bootstrap/esm/FigureCaption";
import { Figure } from "react-bootstrap";
import translateCard from "./translateCard";

class ShowCard extends Component {
    render() {
        let { initial } = this.props;
        let { value, color } = this.props.topDiscard;
        let image;
        if (initial)
            image = process.env.PUBLIC_URL + "/small_cards/0_card_back.png";
        else
            image = process.env.PUBLIC_URL + "/small_cards/" + translateCard(color, value);
        return (
            <div style={{ textAlign: "center", margin: "10px" }}>
                <Figure>
                    <img style={{ border: "1px solid white", height: "5rem" }} src={image} alt={image} title={image} ></img>
                    <FigureCaption style={{ color: "white" }}>Top Card</FigureCaption>
                </Figure>
            </div>
        );
    }
}
export default ShowCard;