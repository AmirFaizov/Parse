import cv2
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Flatten, Conv2D, MaxPooling2D
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.datasets import mnist

# Загрузка изображения
image_path = 'Digits.png'
image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

# Предобработка изображения
_, thresh = cv2.threshold(image, 150, 255, cv2.THRESH_BINARY_INV)
contours, _ = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

# Сортировка контуров слева направо
contours = sorted(contours, key=lambda c: cv2.boundingRect(c)[0])

# Извлечение и отображение цифр
digits = []
for c in contours:
    x, y, w, h = cv2.boundingRect(c)
    digit = thresh[y:y+h, x:x+w]
    digit = cv2.resize(digit, (28, 28))  # Изменение размера до 28x28
    digits.append(digit)

# Отображение извлеченных цифр
plt.figure(figsize=(10, 5))
for i, digit in enumerate(digits):
    plt.subplot(1, len(digits), i + 1)
    plt.imshow(digit, cmap='gray')
    plt.axis('off')
plt.show()

(x_train, y_train), (x_test, y_test) = mnist.load_data()

# Предобработка данных
x_train = x_train.reshape((60000, 28, 28, 1)).astype('float32') / 255.0
x_test = x_test.reshape((10000, 28, 28, 1)).astype('float32') / 255.0
y_train = to_categorical(y_train, num_classes=10)
y_test = to_categorical(y_test, num_classes=10)

# Создание модели нейронной сети
model = Sequential()
model.add(Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1))) # Слой свертки
model.add(MaxPooling2D(pool_size=(2, 2))) # Слой подвыборки
model.add(Flatten()) # Слой выравнивания
model.add(Dense(128, activation='relu')) # Полносвязный слой
model.add(Dense(10, activation='softmax')) # Выходной слой

# Компиляция модели
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# Обучение модели
model.fit(x_train, y_train, epochs=5, batch_size=32, validation_split=0.1)

# Предсказание на извлеченных цифрах
predictions = []
for digit in digits:
    digit = digit.reshape((1, 28, 28, 1)).astype('float32') / 255.0
    pred = model.predict(digit)
    predictions.append(np.argmax(pred))

print("Предсказанные цифры:", predictions)
