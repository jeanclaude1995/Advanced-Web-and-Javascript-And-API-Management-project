import React from "react";
import "./Search.css";

function Search({ handleInput, search }) {
return (
	<div className="search-bar">
	<input
		type="text"
		placeholder="Search for a Movie..."
		className="search"
		onChange={handleInput}
		onKeyPress={search}
	/>
	</div>
);
}

export default Search;
