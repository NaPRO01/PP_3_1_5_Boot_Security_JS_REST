document.addEventListener("DOMContentLoaded", async function () {
    const authUser = await getAuthUser();
    console.log(authUser);
    await fillHeaderText(authUser);
})

function getUserPage() {
    fetch('/api/current_user').then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}

function getInformationAboutUser(user) {
    console.log(user);

    let result = '';
    result =
        `<tr>
    <td>${user.id}</td>
    <td>${user.username}</td>
    <td>${user.lastName}</td>
    <td id=${'name' + user.id}>${user.roles.map(r => r.name.substring(5, r.length)).join(' ')}</td>
</tr>`
    document.getElementById('userTableBody').innerHTML = result;
}

getUserPage();


async function fillHeaderText(authUser) {
    document.getElementById("header_text").innerText =
        `${authUser['username']} with roles: ${getUserRole(authUser['roles'])}`
}

function getUserRole(roles) {
    let result = "";
    for (const role of roles) {
        result += role["name"].replace('ROLE_', '') + " ";
    }
    console.log(result);
    return result;
}

async function getAuthUser() {
    const response = await fetch(`/api/current_user`);
    const authUser = await response.json();
    return authUser;
}