const byId = function (id) {
    return document.getElementById(id)
}
const sel = function (sel) {
    return document.querySelector(sel)
}
const url = "http://localhost:8080/admin/users"

usersTable().then()

async function usersTable() {
    const response = await fetch(url)
    const json = await response.json()
    byId("usersTable").innerHTML = ''

    json.forEach((u) => {
        byId("usersTable").innerHTML += "<tr id=" + 'id' + u.id + ">" +
            '<td >' + u.id + '</td>\n' +
            '<td >' + u.firstName + '</td>\n' +
            '<td>' + u.lastName + '</td>\n' +
            '<td>' + u.age + '</td>\n' +
            '<td>' + u.email + '</td>\n' +
            '<td>' + u.roles.map(roleUser => roleUser.role.replaceAll("ROLE_", "")) + '</td>\n' +
            '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#editModal">Edit</button></td>' +
            '<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">Delete</button></td><tr>';
    })

    json.forEach(u => {
        const btn = document.querySelector(`#id${u.id}`);
        btn.addEventListener('click', click => {
            let form = ((click.target.classList.contains('btn-info')) ? sel('.editForm') : sel('.deleteForm'))
            form.id.value = u.id
            form.firstName.value = u.firstName
            form.lastName.value = u.lastName
            form.age.value = u.age
            form.email.value = u.email
            form.password.value = u.password
        })
    })
}

sel('.createForm').addEventListener('submit', async (evt) => {
    evt.preventDefault();
    let object = {}
    let formData = new FormData(sel('.createForm'))
    formData.forEach((value, key) => {
        if (!Reflect.has(object, key)) {
            if ((key != "roles")) {
                object[key] = value;
                return;
            }
            if (!Array.isArray(object[key])) {
                object[key] = value;
                object[key] = [object[key]];
            } else {
                object[key].push(value);
            }
        }
        if (Array.isArray(object[key])) {
            object[key].push(value);
        }
    });
    await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(object)
    }).then(() => {
        sel('.createForm').reset()
    })
    usersTable().then()
})

sel('.editBTN').addEventListener('click', async (evt) => {
    evt.preventDefault();
    await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            id: byId('editId').value,
            firstName: byId('editFirstName').value,
            lastName: byId('editLastName').value,
            age: byId('editAge').value,
            email: byId('editEmail').value,
            password: byId('editPassword').value,
            roles: Array.from(sel('.editForm').roles.options)
                .filter(option => option.selected)
                .map(option => option.value)
        })
    }).then(res => {
        res.json()
    })
    usersTable().then()
})

sel('.delBTN').addEventListener('click', async (evt) => {
    evt.preventDefault();
    await fetch(url + '/' + byId('deleteId').value, {
        method: 'DELETE'
    }).then((res) => {
        res.json()
    })
    usersTable().then()
})