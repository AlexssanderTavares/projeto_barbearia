import Image from "next/image";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <div className="z-10 w-full max-w-5xl items-center justify-between text-sm lg:flex">
        <p>
          Get started by editing&nbsp;
          <code>app/page.tsx</code>
        </p>
      </div>

      <div className="relative flex place-items-center">
        <Image
          src="/next.svg"
          alt="Next.js Logo"
          width={180}
          height={37}
          priority
        />
      </div>

      <div>
        <a
          href="https://nextjs.org/docs"
          target="_blank"
          rel="noopener noreferrer"
        >
          Read the docs
        </a>
      </div>
    </main>
  );
}