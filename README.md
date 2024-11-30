def find_distance(point1, point2):
    return ((point1[0] - point2[0]) ** 2 + (point1[1] - point2[1]) ** 2) ** 0.5

def find_neighbors(points, point_index, eps):
    neighbors = []
    for i, point in enumerate(points):
        if find_distance(points[point_index], point) < eps:
            neighbors.append(i)
    return neighbors

def expand_cluster(points, cluster_assignment, visited, point_index, neighbors, cluster_id, eps, min_samples):
    for neighbor_index in neighbors:
        if not visited[neighbor_index]:
            visited[neighbor_index] = True
            new_neighbors = find_neighbors(points, neighbor_index, eps)
            if len(new_neighbors) >= min_samples:
                neighbors.extend(new_neighbors)
        if cluster_assignment[neighbor_index] == -1:
            cluster_assignment[neighbor_index] = cluster_id
def dbscan_algorithm(points, eps, min_samples):
    cluster_id = 0
    cluster_assignment = [-1] * len(points)
    visited = [False] * len(points)

    for i, point in enumerate(points):
        if visited[i]:
            continue
        visited[i] = True
        neighbors = find_neighbors(points, i, eps)
        if len(neighbors) < min_samples:
            cluster_assignment[i] = -1
        else:
            cluster_id += 1
            cluster_assignment[i] = cluster_id
            expand_cluster(points, cluster_assignment, visited, i, neighbors, cluster_id, eps, min_samples)

    return cluster_assignment

import pygame
import sys
import dbscan

pygame.init()
eps = 30
min_pts = 3

WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))

points = []
points_colors = []
default_color = "black"
colors = ['red', 'green', 'purple', 'yellow', 'blue']

running = True
while running:
    screen.fill("white")

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        elif event.type == pygame.MOUSEBUTTONDOWN:
            if event.button == 1:
                points.append(event.pos)
                points_colors.append(default_color)

        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_c:
                points = []
                points_colors = []
            elif event.key == pygame.K_RETURN:
                cluster_labels = dbscan.dbscan_algorithm(points, eps, min_pts)
                for i, label in enumerate(cluster_labels):
                    if label == -1:
                        points_colors[i] = "black"  # Шумовые точки
                    else:
                        points_colors[i] = colors[label % len(colors)]  # Остальные точки

    for i, point in enumerate(points):
        pygame.draw.circle(screen, points_colors[i], point, 5)

    pygame.display.flip()

pygame.quit()
sys.exit()
