package me.tungexplorer.study.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import lombok.SneakyThrows;

public class VideoProcessor {
    private String video;

    public VideoProcessor(String video) {
        this.video = video;
        System.out.println("\nNew video processor: " + this.video);
    }

    @SneakyThrows
    public void process() {
        System.out.println(" >>> processing video: " + this.video);
        Thread.sleep(100);
        System.out.println(" >>> Completed process video: " + this.video);
    }

    public String getVideo() {
        return video;
    }
}

class PhantomReferenceEx1 {
    @SneakyThrows
    public static void main(String[] args) {
        String[] videos = new String[]{"video1.mp4", "video2.mp4", "video3.mp4"};

        ReferenceQueue<VideoProcessor> queue = new ReferenceQueue<VideoProcessor>();

        for (String video : videos) {
            VideoProcessor videoProcessor = new VideoProcessor(video);
            PhantomReference<VideoProcessor> phantomRef = new PhantomReference<>(videoProcessor, queue);
            videoProcessor.process();

            videoProcessor = null;
            System.gc();
           /* while (true) {
                boolean isEnqueued = phantomRef.isEnqueued();
                System.out.println(" phantomRef.isEnqueued: " + isEnqueued);

                if (!isEnqueued) {
                    Thread.sleep(1000);
                    continue;
                }
                break;
            }*/
        }

        System.out.println("\nObjects in the queue:");
        Reference<? extends VideoProcessor> ref = null;
        while ((ref = queue.poll()) != null) {
            System.out.println("ref: " + ref +" " + ref.isEnqueued());
        }

    }
}
