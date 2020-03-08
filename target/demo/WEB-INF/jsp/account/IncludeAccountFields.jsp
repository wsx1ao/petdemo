<h3>Account Information</h3>
<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" value="${sessionScope.account.firstName}"></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" value="${sessionScope.account.lastName}"></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="email" value="${sessionScope.account.email}"></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" value="${sessionScope.account.phone}"></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="address1" value="${sessionScope.account.address1}"></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="address2" value="${sessionScope.account.address2}"></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" value="${sessionScope.account.city}"></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="4" name="state" value="${sessionScope.account.state}"></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="10" name="zip" value="${sessionScope.account.zip}"></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="15" name="country" value="${sessionScope.account.country}"></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="languagePreference">
				<option>${language }</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td>
			<select name="favouriteCategoryId">
				<option value="FISH">FISH</option>
				<option selected="selected" value="DOGS">DOGS</option>
				<option value="REPTILES">REPTILES</option>
				<option value="CATS">CATS</option>
				<option value="BIRDS">BIRDS</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<c:if test="${sessionScope.account.listOption}">
			<td><input type="checkbox" name="listOption" checked="checked" value="1"></td>
		</c:if>
		<c:if test="${!sessionScope.account.listOption}">
			<td><input type="checkbox" name="listOption" value="1"></td>
		</c:if>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<c:if test="${sessionScope.account.bannerOption}">
			<td><input type="checkbox" name="bannerOption" checked="checked" value="1"></td>
		</c:if>
		<c:if test="${!sessionScope.account.bannerOption}">
			<td><input type="checkbox" name="bannerOption" value="1"></td>
		</c:if>
	</tr>

</table>
