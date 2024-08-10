import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

"""CONSTANTS"""
END_NUM = 200
LINE_COLOR = "black"


y_data = []
max_length = 0
n:int = 0

for i in range(END_NUM):
    y = i + 1
    sequence = []
    while True:
        sequence.append(y)
        if y == 1:
            break
        elif y % 2 == 0:
            y /= 2
        else:
            y = 3 * y + 1
    y_data.append(sequence)
    max_length = max(max_length, len(sequence))


fig, ax = plt.subplots()
ax.set_xlim(0, max_length)
ax.set_ylim(0, max(max(seq) for seq in y_data))

def update(frame):
    global n
    ax.clear()
    ax.set_xlim(0, max_length)
    ax.set_ylim(0, max(max(seq) for seq in y_data))
    
    cumulative_frames = 0
    
    for i, seq in enumerate(y_data):
        if frame >= cumulative_frames:
            
            frames_for_this_graph = len(seq)
            if frame < cumulative_frames + frames_for_this_graph:
                point_count = frame - cumulative_frames
                ax.plot(range(point_count + 1), seq[:point_count + 1], lw=1, color=LINE_COLOR)
            else:
                ax.plot(range(len(seq)), seq, lw=1, color=LINE_COLOR)
        cumulative_frames += len(seq)
    print(n)
    n+=1
    return ax.lines

def init():
    ax.clear()
    ax.set_xlim(0, max_length)
    ax.set_ylim(0, max(max(seq) for seq in y_data))
    return []

total_frames = sum(len(seq) for seq in y_data)

ani = FuncAnimation(
    fig,
    update,
    frames=total_frames,
    init_func=init,
    blit=False,
    interval=5,
    repeat=False
)

plt.show()
