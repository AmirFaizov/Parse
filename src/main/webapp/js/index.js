const btnAdd = document.querySelector('.btn[data-type = "add"]');
const btnRemove = document.querySelector('.btn[data-type = "remove"]');
const btnSuccess = document.querySelector('.btn-success')
const col = document.querySelector('.col');
const arrCol = col.textContent.split(":");
const sum = document.querySelector('.sum');
const arrSum = sum.textContent.split(" ").map(Number);
const field = document.querySelector(   '.field-value');

btnAdd.addEventListener('click', () => {
    if (arrCol[1] >= 10) {
        alert('Заказать можно не больше 15 штук!')
    } else {
        arrSum[0] += parseInt(field.textContent);
        arrCol[1]++;
        sum.innerHTML = "Сумма заказа: ".bold() + arrSum[0] + ' ' + "руб."
        col.innerHTML = "Вы добавили: ".bold() + arrCol[1] ;
    }
})
btnRemove.addEventListener('click', () => {
    if (arrCol[1] > 0) {
        arrSum[0] -= parseInt(field.textContent);
        arrCol[1]--;
        sum.innerHTML = "Сумма заказа: ".bold() + arrSum[0] + ' ' + "руб."
        col.innerHTML = "Вы добавили: ".bold() + arrCol[1] ;
    }
})
btnSuccess.addEventListener('click', () => {
    if (arrSum[0] !== 0) {
        alert(`Заказ принят! С вас ${arrSum[0]} рублей.`);
        arrSum[0] = 0;
        arrCol[1] = 0;
        sum.innerHTML = "Сумма заказа: ".bold() + arrSum[0] + ' ' + "руб."
        col.innerHTML = "Вы добавили: ".bold() + arrCol[1] ;
    } else {
        alert("Заказ пуст!")
    }
})