function translateCard(color, value) {
    value = value.toLowerCase();
    if (value === "wild")
      return "wild_color_changer.png";
    else if (value === "wild_drawfour")
      return "wild_pick_four.png";
    return translateColor(color) + "_" + translateValue(value) + ".png";
  }
  function translateColor(color) {
    return color.toLowerCase();
  }
  function translateValue(value) {
    let index = [
      "zero",
      "one",
      "two",
      "three",
      "four",
      "five",
      "six",
      "seven",
      "eight",
      "nine",
    ].indexOf(value);
    if (index !== -1) return index.toString();
    if (value === "drawtwo")
      return "draw_2";
    return value;
  }